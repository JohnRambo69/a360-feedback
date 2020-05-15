package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.UserDao;
import com.avenga.a360.model.Participant;
import com.avenga.a360.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName = "a360")
    EntityManager em;

    @Override
    public User getUserByUserName(String userName) {
        User user = null;
        try {
            user = em.createNamedQuery("User.getUserByUserName", User.class)
                    .setParameter("userName", userName)
                    .getSingleResult();
        } catch (Exception e) {
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        try {
            em.persist(user);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean deleteUser(String userName) {

        try {
            User user = null;
            user = getUserByUserName(userName);
            em.remove(user);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean updateRole(String userName, String role) {
    try{
        User user = null;
        user = getUserByUserName(userName);
        user.setRole(role);
        em.persist(user);
        return true;
    }catch(
    Exception e)

    {
        return false;
    }

}




    @Override
    public List<User> getAllUsers(){
        List<User> userList = null;
        try{
            userList = em.createNamedQuery("User.getAllUsers", User.class)
                    .getResultList();
        }catch (Exception e){
        }
        return userList;
    }




}


