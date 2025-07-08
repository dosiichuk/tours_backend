package com.tours.backend.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.tours.backend.domain.Role;
import com.tours.backend.domain.User;
import com.tours.backend.domain.dtos.NewUserDto;
import com.tours.backend.domain.dtos.UserDto;
import com.tours.backend.mapper.UserMapper;
import com.tours.backend.services.AuditService;
import com.tours.backend.services.UserService;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuditService auditService;

    @MockBean
    private UserMapper userMapper;

    @Test
    void getAllUsers() throws Exception {
        User user1 = new User("test@test.com", "password", "fname", "lname", Role.USER);
        User user2 = new User("test2@test.com", "password2", "fname2", "lname2", Role.USER);
        user1.setId(1L);
        user2.setId(2L);
        List<User> users = List.of(user1, user2);

        UserDto userDto1 = new UserDto();
        userDto1.setId(1L);
        userDto1.setEmail(user1.getEmail());
        userDto1.setFirstName(user1.getFirstName());
        userDto1.setLastName(user1.getLastName());

        UserDto userDto2 = new UserDto();
        userDto2.setId(2L);
        userDto2.setEmail(user2.getEmail());
        userDto2.setFirstName(user2.getFirstName());
        userDto2.setLastName(user2.getLastName());

        List<UserDto> userDtos = List.of(userDto1, userDto2);


        when(userService.getAllUsers()).thenReturn(users);
        when(userMapper.mapToDtoList(users)).thenReturn(userDtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("test@test.com"))
                .andExpect(jsonPath("$[0].firstName").value("fname"))
                .andExpect(jsonPath("$[0].lastName").value("lname"));
    }

    @Test
    void getUser() throws Exception {
        User user1 = new User("test@test.com", "password", "fname", "lname", Role.USER);
        user1.setId(1L);
        UserDto userDto1 = new UserDto();

        userDto1.setId(1L);
        userDto1.setEmail(user1.getEmail());
        userDto1.setFirstName(user1.getFirstName());
        userDto1.setLastName(user1.getLastName());

        when(userService.getUserById(1L)).thenReturn(user1);
        when(userMapper.mapToDto(user1)).thenReturn(userDto1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@test.com"));
    }

    @Test
    void registerUser() throws Exception {
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setEmail("newuser@example.com");
        newUserDto.setFirstName("New");
        newUserDto.setLastName("User");
        newUserDto.setPassword("password");

        User user = new User("newuser@example.com", "password", "New", "User", null);
        user.setId(3L);

        UserDto userDto = new UserDto();
        userDto.setId(3L);
        userDto.setEmail("newuser@example.com");
        userDto.setFirstName("New");
        userDto.setLastName("User");
        userDto.setRole(null);

        when(userMapper.mapNewUserDtoToUserEntity(any(NewUserDto.class))).thenReturn(user);
        when(userService.createUser(any(User.class))).thenReturn(user);
        when(userMapper.mapToDto(user)).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"newuser@example.com\",\"firstName\":\"New\",\"lastName\":\"User\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.email").value("newuser@example.com"));
    }

    @Test
    void editUser() throws Exception {
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setId(1L);
        updatedUserDto.setEmail("updated@example.com");
        updatedUserDto.setFirstName("Updated");
        updatedUserDto.setLastName("User");
        updatedUserDto.setRole(null);

        User updatedUser = new User("updated@example.com", "password", "Updated", "User", null);
        updatedUser.setId(1L);

        when(userMapper.mapToEntity(any(UserDto.class))).thenReturn(updatedUser);
        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(updatedUser);
        when(userMapper.mapToDto(updatedUser)).thenReturn(updatedUserDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"email\":\"updated@example.com\",\"firstName\":\"Updated\",\"lastName\":\"User\",\"role\":null}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("updated@example.com"));
    }

    @Test
    void deactivateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        Mockito.verify(userService).deleteUser(1L);
    }
}