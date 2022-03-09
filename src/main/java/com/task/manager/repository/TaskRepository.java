package com.task.manager.repository;

import com.task.manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    Optional<Task> findByTitle(String title);

    Optional<Task> findByTaskBusinessKey(String taskBusinessKey);

}
