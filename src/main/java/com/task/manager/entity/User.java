package com.task.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    private Long id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "USER_BUSINESS_KEY", unique = true)
    private String userBusinessKey;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "TASK_ASSIGNEES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    @JsonIgnore
    private List<Task> tasks;


    public static User createUser(){
        User user = new User();
        user.setUserBusinessKey(UUID.randomUUID().toString());
        return user;
    }

    public User addTask(Task task){
        if (!this.tasks.contains(task)){
            this.tasks.add(task);
        }
        return this;
    }


    public User deleteTask(Task task){
        this.tasks.remove(task);
        return this;
    }

}
