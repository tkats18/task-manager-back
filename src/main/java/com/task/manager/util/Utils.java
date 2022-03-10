package com.task.manager.util;

import com.task.manager.dto.user.UserRepresentationModel;
import com.task.manager.entity.Task;
import com.task.manager.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {

    public static User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserRepresentationModel) authentication.getPrincipal());
    }

    public static DatePair dueToDate(Timeline timeline) {
        if (timeline == null){
            return new DatePair(new Date(SmallLong()), new Date(BigLong()));
        }

        switch (timeline) {
            case OVERDUE:
                return new DatePair(new Date(SmallLong()), new Date());
            case NEAR_DUE:
                return new DatePair(new Date(), new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000));
            case UPCOMING:
                return new DatePair(new Date(), new Date(BigLong()));
        }

        return new DatePair(new Date(SmallLong()), new Date(BigLong()));
    }

    public static Timeline getTimeline(Date dueDate) {
        if (dueDate.before(new Date())){
            return Timeline.OVERDUE;
        }
        if (dueDate.before(new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000))){
            return Timeline.NEAR_DUE;
        }

        return Timeline.UPCOMING;
    }

    public static Long BigLong(){
        return System.currentTimeMillis() + 1000L * 24 * 60 * 60 * 1000;
    }

    public static Long SmallLong(){
        return System.currentTimeMillis() - 1000L * 24 * 60 * 60 * 1000;
    }

    public static List<Task.TaskStatus> AllTaskStatuses(){
        List<Task.TaskStatus> taskStatuses = new ArrayList<>();

        taskStatuses.add(Task.TaskStatus.DONE);
        taskStatuses.add(Task.TaskStatus.NOT_DONE);
        taskStatuses.add(Task.TaskStatus.IN_PROGRESS);

        return taskStatuses;
    }

}
