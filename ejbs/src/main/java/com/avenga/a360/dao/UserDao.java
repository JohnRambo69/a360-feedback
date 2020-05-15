package com.avenga.a360.dao;

import com.avenga.a360.model.User;

import java.util.List;

public interface UserDao {
    User getUserByUserName(String userName);

    boolean addUser(User user);

    boolean deleteUser(String userName);

    boolean updateRole(String userName, String role);

    List<User> getAllUsers();
}
