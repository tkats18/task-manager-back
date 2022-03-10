package com.task.manager.service;

import com.task.manager.dto.task.TaskAddRequest;
import com.task.manager.dto.task.TaskAssignRequest;
import com.task.manager.dto.task.TaskFilterRequest;
import com.task.manager.dto.task.TaskStatusChangeRequest;
import com.task.manager.entity.Task;

public interface TaskService {
    Task assignTask(String taskKey, TaskAssignRequest taskAssignRequest);

    Task changeStatus(String taskKey, TaskStatusChangeRequest taskStatusChangeRequest);

    void deleteTask(String taskKey);

    Object searchTasks(TaskFilterRequest taskFilterRequest);

    Task addTask(TaskAddRequest taskAddRequest);
}
