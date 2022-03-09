package com.task.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TASK")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @SequenceGenerator(name = "SEQ_TASK", sequenceName = "SEQ_TASK", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TASK")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "DUE_DATE", nullable = false)
    private Date dueDate;

    @Column(name = "TASK_BUSINESS_KEY", unique = true)
    private String taskBusinessKey;

    @ManyToMany(mappedBy = "tasks")
    private Set<User> assignees;

    @Column(name = "TASK_STATUS", nullable = false)
    private TaskStatus taskStatus;

    @OneToOne()
    private User creator;

    public static Task createTask() {
        Task task = new Task();
        task.setTaskBusinessKey(UUID.randomUUID().toString());
        task.setTaskStatus(TaskStatus.NOT_DONE);
        return task;
    }


    public enum TaskStatus{
        NOT_DONE("NOT_DONE"),
        IN_PROGRESS("IN_PROGRESS"),
        DONE("DONE");

        private String status;

        TaskStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}
