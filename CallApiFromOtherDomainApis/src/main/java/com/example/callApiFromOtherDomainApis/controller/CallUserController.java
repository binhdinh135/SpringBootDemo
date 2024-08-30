package com.example.callApiFromOtherDomainApis.controller;

import com.example.callApiFromOtherDomainApis.dto.UserResponse;
import com.example.callApiFromOtherDomainApis.user.UserUtil;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CallUserController {

    @Autowired
    private final UserUtil userUtil;

    @GetMapping()
    public List<UserResponse> getUser() {
        List<UserResponse> users = new ArrayList<>();
        try {
            // Lay token
            String token = userUtil.getToken();
            // Dung token de getusers
            users = userUtil.getAllUsers(token);
        } catch (HttpServerErrorException e) {
            int statusCode = e.getStatusCode().value();
            switch (statusCode) {
                // Error UNAUTHORIZED:401, FORBIDDEN:403
                case 401:
                case 403:
                    // Lay token
                    String token = userUtil.getToken();
                    // Dung token de getusers
                    users = userUtil.getAllUsers(token);
                default:
                    throw e;
            }
        }
        return users;
    }

}
