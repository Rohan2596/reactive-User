package com.bridgelabz.reactiveuserservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddUserDto {

    @NotNull(message = "User name can't be null.")
    @NotEmpty(message = "User name can't be empty.")
    public String name;
    public String emailId;
    public String password;
    public String mobileNumber;
}
