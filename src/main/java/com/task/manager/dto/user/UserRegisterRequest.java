package com.task.manager.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class UserRegisterRequest {

    private String email;

    private String fullName;

    private char [] password;
}
