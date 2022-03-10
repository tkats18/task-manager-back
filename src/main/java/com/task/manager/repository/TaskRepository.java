package com.task.manager.repository;

import com.task.manager.entity.Task;
import com.task.manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByTitle(String title);

    Optional<Task> findByTaskBusinessKey(String taskBusinessKey);

    List<Task> findAllByTaskBusinessKeyInAndTaskStatusInAndDueDateBeforeAndDueDateAfter(
            List<String> taskBusinessKey,
            List<Task.TaskStatus> taskStatuses,
            Date leftDate,
            Date rightDate
    );

    List<Task> findAllByCreator(User user);
}
