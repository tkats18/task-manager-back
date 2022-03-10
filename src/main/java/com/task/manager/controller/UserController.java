package com.task.manager.controller;

import com.task.manager.dto.task.TaskFilterRequest;
import com.task.manager.entity.Task;
import com.task.manager.entity.User;
import com.task.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/api/v1/user", produces = "application/json")
@CrossOrigin("*")
public class UserController {

    private UserService userService;

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<User> searchTasks() {
        return userService.searchUsers();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
