package com.transactiondemo.TransactionDemo.service;

import com.transactiondemo.TransactionDemo.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TestNewService {
    void updateUserTwo(User user);

    void updateUserMandatoryTwo(User user);
}
