package com.task.manager.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class UserLoginRequest {


    private String email;

    private char [] password;

}
