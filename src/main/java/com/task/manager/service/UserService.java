package com.task.manager.service;

import com.task.manager.dto.user.UserRegisterRequest;
import com.task.manager.dto.user.UserResponse;
import com.task.manager.entity.User;

import java.util.List;

public interface UserService {

    void addUser(UserRegisterRequest userRegisterRequest);

    User getUser(String email);

    List<UserResponse> searchUsers();

}
