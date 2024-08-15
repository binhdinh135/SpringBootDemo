package com.transactiondemo.TransactionDemo.service;

import com.transactiondemo.TransactionDemo.dto.request.UserRequest;
import com.transactiondemo.TransactionDemo.dto.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);

    void updateUser(Long id, UserRequest request) throws Exception;

    void updateUserSupport(Long id, UserRequest request);

    void updateUserMandatory(Long id, UserRequest request);
}
