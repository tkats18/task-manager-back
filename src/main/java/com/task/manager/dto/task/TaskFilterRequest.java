package com.task.manager.dto.task;

import com.task.manager.entity.Task;
import com.task.manager.util.Timeline;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskFilterRequest {

    private Timeline dueFilter;

    private List<Task.TaskStatus> statusFilter;

    private List<String> userFilter;

}
