package com.task.manager.service.task;

import com.task.manager.dto.task.TaskAddRequest;
import com.task.manager.dto.task.TaskAssignRequest;
import com.task.manager.dto.task.TaskFilterRequest;
import com.task.manager.dto.task.TaskStatusChangeRequest;
import com.task.manager.entity.Task;
import com.task.manager.entity.User;
import com.task.manager.repository.TaskRepository;
import com.task.manager.repository.UserRepository;
import com.task.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    public void setTaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Task assignTask(String taskKey, TaskAssignRequest taskAssignRequest) {
        Task task = getTask(taskKey);

        List<User> users = userRepository.findAllByUserBusinessKeyIn(
                taskAssignRequest.getUsers()
        ).stream().map(user -> user.addTask(task)).collect(Collectors.toList());
        userRepository.saveAll(users);

        task.setAssignees(new HashSet<>(users));
        return taskRepository.save(task);
    }

    @Override
    public Task changeStatus(String taskKey, TaskStatusChangeRequest taskStatusChangeRequest) {
        Task task = getTask(taskKey);
        task.setTaskStatus(taskStatusChangeRequest.getTaskStatus());
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(String taskKey) {
        Task task = getTask(taskKey);

        List<User> users = userRepository.findAll().stream().map(user -> user.deleteTask(task)).collect(Collectors.toList());
        userRepository.saveAll(users);

        taskRepository.delete(task);
    }

    @Override
    public List<Task> searchTasks(TaskFilterRequest taskFilterRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return taskRepository.findAll();
    }

    @Override
    public Task addTask(TaskAddRequest taskAddRequest) {
        if (taskRepository.findByTitle(taskAddRequest.getTitle()).isPresent()) {
            throw new RuntimeException("TASK_EXISTS");
        }

        Task task = Task.createTask();
        task.setDescription(taskAddRequest.getDescription());
        task.setDueDate(taskAddRequest.getDueDate());
        task.setTitle(taskAddRequest.getTitle());

        return taskRepository.save(task);
    }

    private Task getTask(String taskKey) {
        return taskRepository.findByTaskBusinessKey(taskKey).orElseThrow(() ->
                new RuntimeException("TASK_DOES_NOT_EXIST")
        );
    }

}
