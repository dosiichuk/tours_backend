package com.tours.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.tours.backend.controller.exceptions.UserAlreadyExistsException;
import com.tours.backend.controller.exceptions.UserNotFoundException;
import com.tours.backend.domain.User;
import com.tours.backend.domain.dtos.NewUserDto;
import com.tours.backend.domain.dtos.UserDto;
import com.tours.backend.mapper.UserMapper;
import com.tours.backend.services.UserService;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToDtoList(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) throws UserNotFoundException {
        User user =  userService.getUserById(id);
        return ResponseEntity.ok(userMapper.mapToDto(user));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> registerUser(@RequestBody NewUserDto newUser) throws UserAlreadyExistsException {
        User createdUser = userService.createUser(userMapper.mapNewUserDtoToUserEntity(newUser));
        return ResponseEntity.ok(userMapper.mapToDto(createdUser));
    }
    

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login successful for user: ");
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable Long id, @RequestBody UserDto updatedUser) throws UserNotFoundException {
        User user = userService.updateUser(id,  userMapper.mapToEntity(updatedUser));
        return ResponseEntity.ok(userMapper.mapToDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long id)  throws UserNotFoundException {
        userService.deleteUser(id);
        return  ResponseEntity.noContent().build();
    }
}
