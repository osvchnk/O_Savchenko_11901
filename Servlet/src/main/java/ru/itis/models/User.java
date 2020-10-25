package ru.itis.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String first_name;
    private String last_name;
    private Integer age;
    private String login;
    private String password;
    private String uuid;
}
