package com.task.manager.service;

import com.task.manager.dto.GenericResponse;
import com.task.manager.dto.task.TaskAddRequest;
import com.task.manager.dto.task.TaskAssignRequest;
import com.task.manager.dto.task.TaskFilterRequest;
import com.task.manager.dto.task.TaskStatusChangeRequest;
import com.task.manager.entity.Task;
import com.task.manager.entity.User;
import com.task.manager.repository.TaskRepository;
import com.task.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
//    private final  jwtTokenUtil;

    @Autowired
    public void setTaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public GenericResponse assignTask(String taskKey, TaskAssignRequest taskAssignRequest) {
        Task task = getTask(taskKey);
        List<User> users = userRepository.findAllByUserBusinessKeyIn(taskAssignRequest.getUsers());

        task.setAssignees(new HashSet<>(users));
        taskRepository.save(task);
        return GenericResponse.noReturnValue();
    }

    public GenericResponse changeStatus(String taskKey, TaskStatusChangeRequest taskStatusChangeRequest) {
        Task task = getTask(taskKey);
        task.setTaskStatus(taskStatusChangeRequest.getTaskStatus());
        taskRepository.save(task);
        return GenericResponse.noReturnValue();
    }

    public GenericResponse deleteTask(String taskKey) {
        Task task = getTask(taskKey);
        taskRepository.delete(task);
        return GenericResponse.noReturnValue();
    }

    public GenericResponse searchTasks(TaskFilterRequest taskFilterRequest) {
        return null;
    }

    public GenericResponse addTask(TaskAddRequest taskAddRequest) {
        if (taskRepository.findByTitle(taskAddRequest.getTitle()).isPresent()) {
            throw new RuntimeException("TASK_EXISTS");
        }

        Task task = Task.createTask();
        task.setDescription(taskAddRequest.getDescription());
        task.setDueDate(taskAddRequest.getDueDate());
        task.setTitle(taskAddRequest.getTitle());

        taskRepository.save(task);
        return GenericResponse.noReturnValue();
    }

    private Task getTask(String taskKey) {
        return taskRepository.findByTaskBusinessKey(taskKey).orElseThrow(() ->
                new RuntimeException("TASK_DOES_NOT_EXIST")
        );
    }

}
