package com.task.manager.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthorizationResponse {

    private String userBusinessKey;

    private String token;

    private String email;

    private String fullName;

}
