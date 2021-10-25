package ru.dikanskiy.exam.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UsernameAndPasswordAuthorizationRequest {

    private String username;

    private String password;

}
