package com.task.manager.service.auth;

import com.task.manager.dto.user.UserAuthorizationResponse;
import com.task.manager.dto.user.UserLoginRequest;
import com.task.manager.dto.user.UserRegisterRequest;
import com.task.manager.entity.User;
import com.task.manager.service.AuthorizationService;
import com.task.manager.service.task.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private UserServiceImpl userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;

    @Override
    public UserAuthorizationResponse login(@RequestBody UserLoginRequest userLoginRequest){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), Arrays.toString(userLoginRequest.getPassword()))
            );
        }catch (Exception e){
            throw new RuntimeException("INVALID_CREDENTIALS");
        }

        User user = userService.getUser(userLoginRequest.getEmail());
        return new UserAuthorizationResponse(
                user.getUserBusinessKey(),
                jwtTokenService.generateToken(userLoginRequest.getEmail()),
                user.getEmail(),
                user.getFullName()
        );
    }

    @Override
    public UserAuthorizationResponse register(@RequestBody UserRegisterRequest userRegisterRequest){
        String prevPassword = Arrays.toString(userRegisterRequest.getPassword());
        userRegisterRequest.setPassword(new BCryptPasswordEncoder().encode(Arrays.toString(userRegisterRequest.getPassword())).toCharArray());
        userService.addUser(userRegisterRequest);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRegisterRequest.getEmail(),prevPassword)
        );

        User user = userService.getUser(userRegisterRequest.getEmail());
        return new UserAuthorizationResponse(
                user.getUserBusinessKey(),
                jwtTokenService.generateToken(userRegisterRequest.getEmail()),
                user.getEmail(),
                user.getFullName()
        );
    }

    @Override
    public void logout(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            SecurityContextHolder.getContext().setAuthentication(null);
        }else {
            throw new RuntimeException("NOT_LOGGED_IN");
        }
    }


    @Autowired
    public void setUserService(
            UserServiceImpl userService,
            AuthenticationManager authenticationManager,
            JwtTokenService jwtTokenService
    ) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager = authenticationManager;
    }
}
