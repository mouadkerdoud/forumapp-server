package com.duodrek.forumappserver.service;

import com.duodrek.forumappserver.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(Long userId);

    User findByUsername(String username);

    User findByUserId(Long userId);

    List<User> getAllUsers();

    Long numberOfUsers();
}
