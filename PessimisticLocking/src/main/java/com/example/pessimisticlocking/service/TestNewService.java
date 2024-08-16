package com.example.pessimisticlocking.service;


import com.example.pessimisticlocking.entity.User;

public interface TestNewService {
    void updateUserTwo(User user);

    void updateUserMandatoryTwo(User user);
}
