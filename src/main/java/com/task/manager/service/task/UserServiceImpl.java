package com.task.manager.service.task;

import com.task.manager.dto.user.UserRegisterRequest;
import com.task.manager.dto.user.UserResponse;
import com.task.manager.entity.User;
import com.task.manager.repository.UserRepository;
import com.task.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(UserRegisterRequest userRegisterRequest) {

        if (userRepository.findByEmail(userRegisterRequest.getEmail()).isPresent()){
            throw new RuntimeException("USER_EXISTS");
        }

        User user = User.createUser();
        user.setEmail(userRegisterRequest.getEmail());
        user.setFullName(userRegisterRequest.getFullName());
        user.setPassword(String.valueOf(userRegisterRequest.getPassword()));

        userRepository.save(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("USER_NOT_EXISTS"));
    }

    @Override
    public List<UserResponse> searchUsers() {
        return userRepository.findAll().stream().map(UserResponse::new).collect(Collectors.toList());
    }

}
