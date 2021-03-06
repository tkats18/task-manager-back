package com.task.manager.service.task;

import com.task.manager.dto.task.*;
import com.task.manager.entity.Task;
import com.task.manager.entity.User;
import com.task.manager.repository.TaskRepository;
import com.task.manager.repository.UserRepository;
import com.task.manager.service.TaskService;
import com.task.manager.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    public void setTaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public TaskResponse assignTask(String taskKey, TaskAssignRequest taskAssignRequest) {
        Task task = getTask(taskKey);

        List<User> users = userRepository.findAllByUserBusinessKeyIn(
                taskAssignRequest.getUsers()
        ).stream().map(user -> user.addTask(task)).collect(Collectors.toList());
        userRepository.saveAll(users);

        task.setAssignees(new HashSet<>(users));
        return new TaskResponse(taskRepository.save(task));
    }

    @Override
    public TaskResponse changeStatus(String taskKey, TaskStatusChangeRequest taskStatusChangeRequest) {
        Task task = getTask(taskKey);
        task.setTaskStatus(taskStatusChangeRequest.getTaskStatus());

        List<User> userTasks = task.getAssignees().stream().filter(user -> {
            return user.getUserBusinessKey().equals(Utils.getAuthenticatedUser().getUserBusinessKey());
        }).collect(Collectors.toList());

        if (userTasks.size() == 0) {
            throw new RuntimeException("NOT_YOUR_TASK");
        }
        return new TaskResponse(taskRepository.save(task));
    }

    @Override
    public void deleteTask(String taskKey) {
        Task task = getTask(taskKey);
        List<User> users = userRepository.findAll().stream().map(user -> user.deleteTask(task)).collect(Collectors.toList());

        userRepository.saveAll(users);
        taskRepository.delete(task);
    }

    @Override
    public List<TaskResponse> searchTasks(TaskFilterRequest taskFilterRequest) {
        User authenticatedUser = Utils.getAuthenticatedUser();

        Set<String> taskIds = mergerTaskFilters(taskFilterRequest, authenticatedUser);
        List<Task.TaskStatus> taskStatuses = taskFilterRequest.getStatusFilter() == null || taskFilterRequest.getStatusFilter().size() == 0 ? Utils.AllTaskStatuses() : taskFilterRequest.getStatusFilter();

        return taskRepository.findAllByTaskBusinessKeyInAndTaskStatusInAndDueDateBeforeAndDueDateAfter(
                new ArrayList<>(taskIds),
                taskStatuses,
                Utils.dueToDate(taskFilterRequest.getDueFilter()).getRightDate(),
                Utils.dueToDate(taskFilterRequest.getDueFilter()).getLeftDate()
        ).stream().map(TaskResponse::new).collect(Collectors.toList());
    }

    @Override
    public TaskResponse addTask(TaskAddRequest taskAddRequest) {
        if (taskRepository.findByTitle(taskAddRequest.getTitle()).isPresent()) {
            throw new RuntimeException("TASK_EXISTS");
        }

        Task task = Task.createTask();
        task.setCreator(Utils.getAuthenticatedUser());
        task.setDescription(taskAddRequest.getDescription());
        task.setDueDate(taskAddRequest.getDueDate());
        task.setTitle(taskAddRequest.getTitle());

        return new TaskResponse(taskRepository.save(task));
    }

    private Task getTask(String taskKey) {
        return taskRepository.findByTaskBusinessKey(taskKey).orElseThrow(() ->
                new RuntimeException("TASK_DOES_NOT_EXIST")
        );
    }

    private Set<String> taskIds(List<String> userBusinessKeys) {
        if (userBusinessKeys == null || userBusinessKeys.size() == 0) {
            return new HashSet<>();
        }

        Set<String> taskIds = new HashSet<>();
        List<User> users = userRepository.findAllByUserBusinessKeyIn(userBusinessKeys);
        for (User user : users) {
            taskIds.addAll(user.getTasks().stream().map(Task::getTaskBusinessKey).collect(Collectors.toList()));
        }
        return taskIds;
    }

    private Set<String> mergerTaskFilters(TaskFilterRequest taskFilterRequest, User authenticatedUser) {
        List<Task> creatorTasks = taskRepository.findAllByCreator(authenticatedUser);

        Set<String> taskIds = taskIds(Collections.singletonList(authenticatedUser.getUserBusinessKey()));
        Set<String> userFilterTasks = taskIds(taskFilterRequest.getUserFilter()) ;

        for (Task task : creatorTasks) {
            taskIds.add(task.getTaskBusinessKey());
        }
        if (userFilterTasks.size() != 0){
            taskIds = userFilterTasks.stream().filter(taskIds::contains).collect(Collectors.toSet());
        }
        return taskIds;
    }

}
