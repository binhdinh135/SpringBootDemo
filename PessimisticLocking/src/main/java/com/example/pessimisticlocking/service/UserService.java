package com.example.pessimisticlocking.service;


import com.example.pessimisticlocking.dto.request.UserRequest;
import com.example.pessimisticlocking.dto.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);

    void updateUser(Long id, UserRequest request) throws Exception;

    void updateUserSupport(Long id, UserRequest request);

    void updateUserMandatory(Long id, UserRequest request);
}
