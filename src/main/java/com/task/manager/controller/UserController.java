package com.task.manager.controller;

import com.task.manager.dto.GenericResponse;
import com.task.manager.dto.user.UserLoginRequest;
import com.task.manager.dto.user.UserRegisterRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/api/v1/user", produces = "application/json")
public class UserController {

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public GenericResponse register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return null;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public GenericResponse login(@RequestBody UserLoginRequest userLoginRequest) {
        return null;
    }


}
