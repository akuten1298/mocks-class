package com.example;

public interface UserRepository {
    User findUserById(int id);
    void save(User user);
}

