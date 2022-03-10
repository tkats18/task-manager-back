package com.task.manager.service;

import com.task.manager.dto.task.*;

public interface TaskService {
    TaskResponse assignTask(String taskKey, TaskAssignRequest taskAssignRequest);

    TaskResponse changeStatus(String taskKey, TaskStatusChangeRequest taskStatusChangeRequest);

    void deleteTask(String taskKey);

    Object searchTasks(TaskFilterRequest taskFilterRequest);

    TaskResponse addTask(TaskAddRequest taskAddRequest);
}
