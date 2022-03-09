package com.task.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.task.manager"})
@Configuration
@EntityScan("com.task.manager.entity")
@EnableJpaRepositories(basePackages = "com.task.manager.repository")
public class TaskManagerBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerBackApplication.class, args);
    }

}
