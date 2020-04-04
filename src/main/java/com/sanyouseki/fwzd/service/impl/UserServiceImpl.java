package com.sanyouseki.fwzd.service.impl;

import com.sanyouseki.fwzd.dao.UserMapper;
import com.sanyouseki.fwzd.entity.User;
import com.sanyouseki.fwzd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User entity) {
        userMapper.add(entity.getName(), entity.getPassword());
    }

    @Override
    public void deleteUserById(int id) {
        userMapper.safeDelete(1, id);
    }

    @Override
    public void deleteUserByName(String name) {
        userMapper.safeDeleteByName(1, name);
    }

    @Override
    public void updateUser(User entity) {
        userMapper.update(entity.getName(), entity.getPassword(), entity.getId());
    }

    @Override
    public User getUserById(int id) {
        return userMapper.findUser(id);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.findUserList();
    }
}
