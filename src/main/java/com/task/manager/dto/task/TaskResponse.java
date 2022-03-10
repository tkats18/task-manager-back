package com.task.manager.dto.task;

import com.task.manager.dto.user.UserResponse;
import com.task.manager.entity.Task;
import com.task.manager.entity.User;
import com.task.manager.util.Timeline;
import com.task.manager.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskResponse {

    private String title;

    private String description;

    private Date dueDate;

    private String taskBusinessKey;

    private List<UserResponse> assignees;

    private Task.TaskStatus taskStatus;

    private UserResponse creator;

    private Timeline timeline;

    public TaskResponse(Task task) {
        setTitle(task.getTitle());
        setDescription(task.getDescription());
        setTaskStatus(task.getTaskStatus());
        setAssignees(task.getAssignees() == null ? Collections.emptyList() : task.getAssignees().stream().map(UserResponse::new).collect(Collectors.toList()));
        setCreator(new UserResponse(task.getCreator()));
        setTaskBusinessKey(task.getTaskBusinessKey());
        setDueDate(task.getDueDate());
        setTimeline(Utils.getTimeline(task.getDueDate()));
    }

}
