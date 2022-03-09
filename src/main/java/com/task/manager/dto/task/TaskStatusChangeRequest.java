package com.task.manager.dto.task;

import com.task.manager.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatusChangeRequest {

    private Task.TaskStatus taskStatus;

}
