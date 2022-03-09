package com.task.manager.controller;

import com.task.manager.dto.GenericResponse;
import com.task.manager.dto.user.UserLoginRequest;
import com.task.manager.dto.user.UserRegisterRequest;
import com.task.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/api/v1/user", produces = "application/json")
public class UserController {

    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public GenericResponse register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.register(userRegisterRequest);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public GenericResponse login(@RequestBody UserLoginRequest userLoginRequest) {
        return userService.login(userLoginRequest);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
