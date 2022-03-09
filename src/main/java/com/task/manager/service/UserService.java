package com.task.manager.service;

import com.task.manager.dto.GenericResponse;
import com.task.manager.dto.user.UserLoginRequest;
import com.task.manager.dto.user.UserRegisterRequest;
import com.task.manager.entity.User;
import com.task.manager.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public GenericResponse register(UserRegisterRequest userRegisterRequest) {

        if (userRepository.findByEmail(userRegisterRequest.getEmail()).isPresent()){
            throw new RuntimeException("USER_EXISTS");
        }

        User user = User.createUser();
        user.setEmail(userRegisterRequest.getEmail());
        user.setFullName(userRegisterRequest.getFullName());
        user.setPassword(DigestUtils.sha256Hex(Arrays.toString(userRegisterRequest.getPassword())));

        userRepository.save(user);
        return GenericResponse.noReturnValue();
    }

    public GenericResponse login(UserLoginRequest userLoginRequest) {
        return null;
    }

}
