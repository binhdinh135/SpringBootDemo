package com.example.oneservicetwodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list-all")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        var result = userService.findAllUsers();
        return ResponseEntity.ok(result);
    }


}
