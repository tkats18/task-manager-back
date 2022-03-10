package com.task.manager.service.task;

import com.task.manager.dto.GenericResponse;
import com.task.manager.dto.user.UserRegisterRequest;
import com.task.manager.entity.User;
import com.task.manager.repository.UserRepository;
import com.task.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GenericResponse addUser(UserRegisterRequest userRegisterRequest) {

        if (userRepository.findByEmail(userRegisterRequest.getEmail()).isPresent()){
            throw new RuntimeException("USER_EXISTS");
        }

        User user = User.createUser();
        user.setEmail(userRegisterRequest.getEmail());
        user.setFullName(userRegisterRequest.getFullName());
        user.setPassword(Arrays.toString(userRegisterRequest.getPassword()));

        userRepository.save(user);
        return GenericResponse.noReturnValue();
    }

    @Override
    public User getUser(String email) {
        return null;
    }

}
