package com.example.callApiFromOtherDomainApis.controller;

import com.example.callApiFromOtherDomainApis.dto.UserResponse;
import com.example.callApiFromOtherDomainApis.user.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CallUserController {

    private final UserUtil userUtil;

    @GetMapping()
    public List<UserResponse> getUser() {
        List<UserResponse> users = new ArrayList<>();
        // Lay token
        String token = userUtil.getToken();
        // Dung token de getusers
        users = userUtil.getAllUsers(token);
        return users;
    }

}
