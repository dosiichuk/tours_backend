package com.tours.backend.mapper;

import com.tours.backend.domain.Role;
import com.tours.backend.domain.User;
import com.tours.backend.domain.dtos.NewUserDto;
import com.tours.backend.domain.dtos.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private final UserMapper userMapper = new UserMapper();

    @Test
    void testMapToDto() {
        User user = new User("test@test.com", "password", "John", "Doe", Role.USER);
        user.setId(1L);

        UserDto userDto = userMapper.mapToDto(user);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getRole().getRole(), userDto.getRole());
    }

    @Test
    void testMapToEntity() {
        UserDto userDto = new UserDto();
        userDto.setId(2L);
        userDto.setFirstName("Jane");
        userDto.setLastName("Smith");
        userDto.setEmail("jane@smith.com");
        userDto.setRole("USER");

        User user = userMapper.mapToEntity(userDto);

        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void testMapToDtoList() {
        User user1 = new User("a@a.com", "pass", "A", "B", Role.USER);
        user1.setId(1L);
        User user2 = new User("b@b.com", "pass", "B", "C", Role.ADMIN);
        user2.setId(2L);
        List<User> users = Arrays.asList(user1, user2);

        List<UserDto> userDtos = userMapper.mapToDtoList(users);

        assertEquals(users.size(), userDtos.size());
        assertEquals(user1.getId(), userDtos.get(0).getId());
        assertEquals(user2.getId(), userDtos.get(1).getId());
        assertEquals(user1.getEmail(), userDtos.get(0).getEmail());
        assertEquals(user2.getEmail(), userDtos.get(1).getEmail());
    }

    @Test
    void testMapToEntityList() {
        UserDto userDto1 = new UserDto();
        userDto1.setId(1L);
        userDto1.setFirstName("A");
        userDto1.setLastName("B");
        userDto1.setEmail("a@a.com");
        userDto1.setRole("USER");

        UserDto userDto2 = new UserDto();
        userDto2.setId(2L);
        userDto2.setFirstName("B");
        userDto2.setLastName("C");
        userDto2.setEmail("b@b.com");
        userDto2.setRole("ADMIN");

        List<UserDto> userDtos = Arrays.asList(userDto1, userDto2);

        List<User> users = userMapper.mapToEntityList(userDtos);

        assertEquals(userDtos.size(), users.size());
        assertEquals(userDto1.getId(), users.get(0).getId());
        assertEquals(userDto2.getId(), users.get(1).getId());
        assertEquals(userDto1.getEmail(), users.get(0).getEmail());
        assertEquals(userDto2.getEmail(), users.get(1).getEmail());
    }

    @Test
    void testMapNewUserDtoToUserEntity() {
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setEmail("new@user.com");
        newUserDto.setFirstName("New");
        newUserDto.setLastName("User");
        newUserDto.setPassword("secret");
        newUserDto.setRole("ADMIN");

        User user = userMapper.mapNewUserDtoToUserEntity(newUserDto);

        assertEquals(newUserDto.getEmail(), user.getEmail());
        assertEquals(newUserDto.getFirstName(), user.getFirstName());
        assertEquals(newUserDto.getLastName(), user.getLastName());
        assertEquals(newUserDto.getPassword(), user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
    }
}