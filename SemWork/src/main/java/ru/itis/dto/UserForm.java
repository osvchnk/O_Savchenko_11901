package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Length(min = 3, max = 30)
    private String firstName;

    @NotEmpty
    @Length(min = 3, max = 30)
    private String lastname;

    @NotEmpty
    @Length(min = 8)
    private String password;
}
