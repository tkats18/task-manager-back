package com.task.manager.controller;

import com.task.manager.dto.GenericResponse;
import com.task.manager.dto.task.TaskAddRequest;
import com.task.manager.dto.task.TaskAssignRequest;
import com.task.manager.dto.task.TaskFilterRequest;
import com.task.manager.dto.task.TaskStatusChangeRequest;
import com.task.manager.entity.Task;
import com.task.manager.service.task.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/api/v1/task", produces = "application/json")
@CrossOrigin("*")
public class TaskController {

    private TaskServiceImpl taskService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Task addTask(@RequestBody TaskAddRequest taskAddRequest) {
        return taskService.addTask(taskAddRequest);
    }

    @RequestMapping(value = "{taskKey}/assign", method = RequestMethod.PUT)
    public Task assignTask(@PathVariable String taskKey, @RequestBody TaskAssignRequest taskAssignRequest) {
        return taskService.assignTask(taskKey, taskAssignRequest);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public List<Task> searchTasks(@RequestBody TaskFilterRequest taskFilterRequest) {
        return taskService.searchTasks(taskFilterRequest);
    }

    @RequestMapping(value = "{taskKey}/delete", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable String taskKey) {
        taskService.deleteTask(taskKey);
    }

    @RequestMapping(value = "{taskKey}/status", method = RequestMethod.PUT)
    public GenericResponse changeStatus(@PathVariable String taskKey, @RequestBody TaskStatusChangeRequest taskStatusChangeRequest) {
        return GenericResponse.successObject(taskService.changeStatus(taskKey, taskStatusChangeRequest));
    }

    @Autowired
    public void setTaskService(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }
}
