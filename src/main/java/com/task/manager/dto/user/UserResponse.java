package com.task.manager.dto.user;


import com.task.manager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String email;

    private String fullName;

    private String userBusinessKey;

    public UserResponse(User user) {
        setEmail(user.getEmail());
        setFullName(user.getFullName());
        setUserBusinessKey(user.getUserBusinessKey());
    }

}
