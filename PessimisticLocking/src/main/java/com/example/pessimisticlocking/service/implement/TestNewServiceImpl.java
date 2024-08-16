package com.example.pessimisticlocking.service.implement;

import com.example.pessimisticlocking.entity.User;
import com.example.pessimisticlocking.repository.UserRepository;
import com.example.pessimisticlocking.service.TestNewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestNewServiceImpl implements TestNewService {

    private final UserRepository userRepository;

    public void updateUserTwo (User user) {
        user.setPassword("binhcheck");
        userRepository.save(user);

    }
    @Override
    public void updateUserMandatoryTwo(User user) {
        userRepository.save(user);
    }

}
