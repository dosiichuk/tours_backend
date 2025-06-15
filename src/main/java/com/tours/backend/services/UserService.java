package com.tours.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tours.backend.controller.exceptions.UserAlreadyExistsException;
import com.tours.backend.controller.exceptions.UserNotFoundException;
import com.tours.backend.domain.User;
import com.tours.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user)  throws UserAlreadyExistsException {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
        }
        
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public User updateUser(Long id, User userDetails) throws UserNotFoundException {
        User user = getUserById(id);
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        User user = getUserById(id);
        userRepository.delete(user);
    }

}
