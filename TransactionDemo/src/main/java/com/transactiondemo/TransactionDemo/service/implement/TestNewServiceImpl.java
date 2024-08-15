package com.transactiondemo.TransactionDemo.service.implement;

import com.transactiondemo.TransactionDemo.entity.User;
import com.transactiondemo.TransactionDemo.repository.UserRepository;
import com.transactiondemo.TransactionDemo.service.TestNewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
// Check transaction information: TransactionAspectSupport.currentTransactionInfo()

public class TestNewServiceImpl implements TestNewService {

    private final UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateUserTwo (User user) {
        user.setPassword("binhcheck");
        userRepository.save(user);

    }

}
