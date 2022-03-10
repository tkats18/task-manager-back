package com.task.manager.service;

import com.task.manager.dto.GenericResponse;
import com.task.manager.dto.user.UserRegisterRequest;
import com.task.manager.entity.User;

import java.util.List;

public interface UserService {
    GenericResponse addUser(UserRegisterRequest userRegisterRequest);

    User getUser(String email);

    List<User> searchUsers();
}
