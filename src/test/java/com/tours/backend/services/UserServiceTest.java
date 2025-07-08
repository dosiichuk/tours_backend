package com.tours.backend.services;

import com.tours.backend.controller.exceptions.UserAlreadyExistsException;
import com.tours.backend.controller.exceptions.UserNotFoundException;
import com.tours.backend.domain.Role;
import com.tours.backend.domain.User;
import com.tours.backend.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");
        user.setPassword("password");
        user.setRole(Role.USER);
    }

    @Test
    public void testCreateUser_Success() throws UserAlreadyExistsException {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);

        User created = userService.createUser(user);

        assertEquals(user, created);
        verify(userRepository).save(user);
    }

    @Test
    public void testCreateUser_UserAlreadyExists() throws UserAlreadyExistsException {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        assertThrows(UserAlreadyExistsException.class, () -> {
            userService.createUser(user);
        });
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
    }

    @Test
    public void testGetUserById_Success() throws UserNotFoundException {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User found = userService.getUserById(1L);

        assertEquals(user, found);
    }

    @Test
    public void testGetUserById_NotFound() throws UserNotFoundException {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(2L);
        });
    }

    @Test
    public void testUpdateUser_Success() throws UserNotFoundException {
        User updatedDetails = new User();
        updatedDetails.setFirstName("Jane");
        updatedDetails.setLastName("Smith");
        updatedDetails.setEmail("jane@example.com");
        updatedDetails.setPassword("newpass");
        updatedDetails.setRole(Role.ADMIN);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User updated = userService.updateUser(1L, updatedDetails);

        assertEquals("Jane", updated.getFirstName());
        assertEquals("Smith", updated.getLastName());
        assertEquals("jane@example.com", updated.getEmail());
        assertEquals("newpass", updated.getPassword());
        assertEquals(Role.ADMIN, updated.getRole());
    }

    @Test
    public void testUpdateUser_NotFound() throws UserNotFoundException {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(2L, user);
        });
    }

    @Test
    public void testDeleteUser_Success() throws UserNotFoundException {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(1L);

        verify(userRepository).delete(user);
    }

    @Test
    public void testDeleteUser_NotFound() throws UserNotFoundException {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser(2L);
        });
    }
}