package com.avenga.a360.service;

import com.avenga.a360.dto.UserDto;
import com.avenga.a360.model.User;

import java.util.List;

public interface UserService {
    User getUserByUsername(String userName);

    boolean addUser(UserDto userDto);

    boolean updateUserRole(String userName, String role);

    boolean removeUser(String userName);

    List<User> getAllUsers();

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    List<UserDto> userListToUserDtoList(List<User> userList);
}
