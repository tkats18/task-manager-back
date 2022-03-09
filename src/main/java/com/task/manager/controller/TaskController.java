package com.task.manager.controller;

import com.task.manager.dto.GenericResponse;
import com.task.manager.dto.task.TaskAddRequest;
import com.task.manager.dto.task.TaskAssignRequest;
import com.task.manager.dto.task.TaskFilterRequest;
import com.task.manager.dto.task.TaskStatusChangeRequest;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/api/v1/task", produces = "application/json")
public class TaskController {

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public GenericResponse addTask(@RequestBody TaskAddRequest taskAddRequest) {
        return null;
    }

    @RequestMapping(value = "{taskId}/assign", method = RequestMethod.PUT)
    public GenericResponse assignTask(@PathVariable Long taskId, @RequestBody TaskAssignRequest taskAssignRequest) {
        return null;
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public GenericResponse searchTasks(@RequestBody TaskFilterRequest taskFilterRequest) {
        return null;
    }

    @RequestMapping(value = "{taskId}/delete", method = RequestMethod.DELETE)
    public GenericResponse deleteTask(@PathVariable Long taskId) {
        return null;
    }

    @RequestMapping(value = "{taskId}/status", method = RequestMethod.PUT)
    public GenericResponse changeStatus(@PathVariable Long taskId, @RequestBody TaskStatusChangeRequest taskStatusChangeRequest) {
        return null;
    }

}
