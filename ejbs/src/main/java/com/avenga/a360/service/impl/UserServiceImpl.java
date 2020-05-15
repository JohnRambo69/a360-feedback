package com.avenga.a360.service.impl;

import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.dao.UserDao;
import com.avenga.a360.dto.UserDto;
import com.avenga.a360.model.Participant;
import com.avenga.a360.model.User;
import com.avenga.a360.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserServiceImpl implements UserService{
    @Inject
    UserDao userDao;

    @Override
    public User getUserByUsername(String userName) {

        return (userDao.getUserByUserName(userName));
    }

    @Override
    public boolean addUser(UserDto userDto){
    User user = userDtoToUser(userDto);
        return (userDao.addUser(user));
    }

    @Override
    public boolean updateUserRole(String userName, String role){
        return userDao.updateRole(userName,role);
    }

    @Override
    public boolean removeUser(String userName){
        return (userDao.deleteUser(userName));
    }


    @Override
    public List<User> getAllUsers(){
      return userDao.getAllUsers();
    }

    @Override
    public UserDto userToUserDto(User user){
        UserDto userDto = new UserDto();
        //userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setUserName(user.getUserName());
        return userDto;
    }

    @Override
    public User userDtoToUser(UserDto userDto){
        User user = new User();
        user.setRole(userDto.getRole());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        return user;
    }

    @Override
    public List<UserDto> userListToUserDtoList(List<User> userList){
        List<UserDto> userDtoList = new ArrayList<>();
        if(userList !=null){
            for(User u : userList){
                UserDto userDto = userToUserDto(u);
                userDtoList.add(userDto);
            }
        }
        return userDtoList;

    }

}
