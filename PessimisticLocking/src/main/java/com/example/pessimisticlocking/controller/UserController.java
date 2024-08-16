package com.example.pessimisticlocking.controller;

import com.example.pessimisticlocking.dto.request.UserRequest;
import com.example.pessimisticlocking.dto.response.UserResponse;
import com.example.pessimisticlocking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {


    private final UserService userService;

    @PostMapping
    UserResponse createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{id}")
    public void updateAuthorName(@PathVariable Long id, @RequestBody UserRequest request) throws Exception {
        userService.updateUser(id, request);
    }

    @Transactional()
    @PutMapping("/support/{id}")
    public void updateAuthorNameSupport(@PathVariable Long id, @RequestBody UserRequest request) throws Exception {
        userService.updateUserSupport(id, request);
    }

    @Transactional()
    @PutMapping("/mandatory/{id}")
    public void updateAuthorNameMandatory(@PathVariable Long id, @RequestBody UserRequest request) throws Exception {
        userService.updateUserMandatory(id, request);
    }
}
