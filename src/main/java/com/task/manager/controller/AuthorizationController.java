package com.task.manager.controller;

import com.task.manager.dto.user.UserAuthorizationResponse;
import com.task.manager.dto.user.UserLoginRequest;
import com.task.manager.dto.user.UserRegisterRequest;
import com.task.manager.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/api/v1/auth", produces = "application/json")
@CrossOrigin("*")
public class AuthorizationController {

    private AuthorizationService authorizationService;


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public UserAuthorizationResponse login(@RequestBody UserLoginRequest userLoginRequest){
        return authorizationService.login(userLoginRequest);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public UserAuthorizationResponse register(@RequestBody UserRegisterRequest userRegisterRequest){
        return authorizationService.register(userRegisterRequest);
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public void logout(){
        authorizationService.logout();
    }


    @Autowired
    public void setUserService(
            AuthorizationService authorizationService
    ) {
        this.authorizationService = authorizationService;
    }
}
