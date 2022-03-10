package com.task.manager.service;

import com.task.manager.dto.user.UserAuthorizationResponse;
import com.task.manager.dto.user.UserLoginRequest;
import com.task.manager.dto.user.UserRegisterRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthorizationService {

    UserAuthorizationResponse login(@RequestBody UserLoginRequest userLoginRequest);

    UserAuthorizationResponse register(@RequestBody UserRegisterRequest userRegisterRequest);

    void logout();
}
