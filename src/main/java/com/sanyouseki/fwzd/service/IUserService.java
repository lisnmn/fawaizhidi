package com.sanyouseki.fwzd.service;

import com.sanyouseki.fwzd.entity.User;

import java.util.List;

public interface IUserService {
    void addUser(User entity);
    void deleteUserById(int id);
    void deleteUserByName(String name);
    void updateUser(User entity);
    User getUserById(int id);
    User getUserByName(String name);
    List<User> getAllUsers();
}
