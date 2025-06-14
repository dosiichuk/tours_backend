package com.tours.backend.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tours.backend.domain.Role;
import com.tours.backend.domain.User;
import com.tours.backend.domain.UserRole;
import com.tours.backend.domain.dtos.NewUserDto;
import com.tours.backend.domain.dtos.UserDto;

@Service
public class UserMapper {

    public UserDto mapToDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().getRole().getRoleString());

        return userDto;
    }

    public User mapToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        UserRole userRole = UserRole.fromString(userDto.getRole());
        Role role = new Role(userRole);
        user.setRole(role);

        return user;
    }

    public List<UserDto> mapToDtoList(List<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<User> mapToEntityList(List<UserDto> userDtos) {
        if (userDtos == null) {
            return null;
        }
        return userDtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }

    public User mapNewUserDtoToUserEntity(NewUserDto newUserDto) {
        if (newUserDto == null) {
            return null;
        }
        User user = new User();
        user.setEmail(newUserDto.getEmail());
        user.setFirstName(newUserDto.getFirstName());
        user.setLastName(newUserDto.getLastName());
        user.setPassword(newUserDto.getPassword());
        if (newUserDto.getRole() != null) {
            UserRole userRole = UserRole.fromString(newUserDto.getRole());
            user.setRole(new Role(userRole));
        }
        return user;
    }

}
