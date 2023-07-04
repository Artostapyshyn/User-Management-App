package com.artostapyshyn.usermanagementapp.service;

import com.artostapyshyn.usermanagementapp.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    User save(User user);

    void registerUser(User user);

    void updateUser(User updatedUser);

    String getCurrentUserEmail();
}
