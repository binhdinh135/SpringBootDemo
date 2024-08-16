package com.example.pessimisticlocking.service.implement;

import com.example.pessimisticlocking.dto.request.UserRequest;
import com.example.pessimisticlocking.dto.response.UserResponse;
import com.example.pessimisticlocking.entity.User;
import com.example.pessimisticlocking.repository.UserRepository;
import com.example.pessimisticlocking.service.TestNewService;
import com.example.pessimisticlocking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final TestNewService testService;

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
        userRepository.save(user);
        return null;
    }

    @Override
    public void updateUser(Long id, UserRequest request) throws Exception {
        Optional<User> userOp = userRepository.findById(id);
        if (userOp.isPresent()) {
            User user = userOp.get();
//            user.setUsername(request.getUsername());
//            user.setPassword(request.getPassword());
//            userRepository.save(user);
            testService.updateUserTwo(user);
//            updateUserTwo(user);
//            throw new Exception();
            throw new RuntimeException();
        }
    }

    @Override
    public void updateUserSupport(Long id, UserRequest request) {
        Optional<User> userOp = userRepository.findById(id);
        if (userOp.isPresent()) {
            User user = userOp.get();
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
            updateUserSupportTwo(user);
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void updateUserSupportTwo(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUserMandatory(Long id, UserRequest request) {
        Optional<User> userOp = userRepository.findById(id);
        if (userOp.isPresent()) {
            User user = userOp.get();
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
//            updateUserMandatoryTwo(user);
            testService.updateUserMandatoryTwo(user);
//            throw new RuntimeException();
        }

    }

    public void updateUserMandatoryTwo(User user) {
        userRepository.save(user);
    }


}
