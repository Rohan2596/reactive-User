package com.bridgelabz.reactiveuserservice.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotNull(message = "Email Address can't be null.")
    @NotEmpty(message = "Email Address can't be empty.")
    @Email(message = "Email Address pattern doesn't match.")
    @Pattern(regexp = "[a-zA-Z0-9.]{1,}+[@]{1}+[a-zA-Z0-9.]{1,}$",message = "Email id Pattern Doesn't match.")
    public String emailId;

    @NotNull(message = "Password can't be null.")
    @NotEmpty(message = "Password can't be empty.")
    @Pattern(regexp = "[a-zA-Z0-9@#$%*]{8,25}",message = "Password Pattern must doesn't match and size 8 to 25.")
    public String password;

}
