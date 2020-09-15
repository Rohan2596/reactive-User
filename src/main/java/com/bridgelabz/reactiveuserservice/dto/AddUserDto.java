package com.bridgelabz.reactiveuserservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto  {


    @NotNull(message = "User name can't be null.")
    @NotEmpty(message = "User name can't be empty.")
    @Pattern(regexp = "[a-zA-Z\\s]{3,25}$",message = "User name can only contains alphabets.")
    @Size(min = 3,max = 25,message = "User name size between 3 to 25.")
    public String name;

    @NotNull(message = "Email Address can't be null.")
    @NotEmpty(message = "Email Address can't be empty.")
    @Email(message = "Email Address pattern doesn't match.")
    @Pattern(regexp = "[a-zA-Z0-9.]{1,}+[@]{1}+[a-zA-Z0-9.]{1,}$",message = "Email id Pattern Does'nt match.")
    public String emailId;

    @NotNull(message = "Password can't be null.")
    @NotEmpty(message = "Password can't be empty.")
    public String password;

    @NotNull(message = "Mobile Number can't be null.")
    @NotEmpty(message = "Mobile Number can't be empty.")
    public String mobileNumber;
}
