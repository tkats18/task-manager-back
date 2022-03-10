package com.task.manager.controller;

import com.task.manager.dto.GenericResponse;
import com.task.manager.dto.user.UserLoginRequest;
import com.task.manager.dto.user.UserRegisterRequest;
import com.task.manager.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/api/v1/auth", produces = "application/json")
public class AuthorizationController {

    private AuthorizationService authorizationService;


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public GenericResponse login(@RequestBody UserLoginRequest userLoginRequest){
        return GenericResponse.successObject(authorizationService.login(userLoginRequest));
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public GenericResponse register(@RequestBody UserRegisterRequest userRegisterRequest){
        return GenericResponse.successObject(authorizationService.register(userRegisterRequest));
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public GenericResponse logout(){
        authorizationService.logout();
        return GenericResponse.noReturnValue();
    }


    @Autowired
    public void setUserService(
            AuthorizationService authorizationService
    ) {
        this.authorizationService = authorizationService;
    }
}
