package com.example.oneservicetwodb.controller;

import com.example.oneservicetwodb.database1.IUserRepository;
import com.example.oneservicetwodb.database1.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            List<UserDTO> userDTOs = new ArrayList<UserDTO>();
            for (User user : users) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(user.getId());
                userDTO.setUsername(user.getUsername());
                userDTO.setPassword(user.getPassword());
                userDTOs.add(userDTO);
            }
            return userDTOs;
        }

        return Collections.emptyList();
    }
}
