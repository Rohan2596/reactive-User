package com.bridgelabz.reactiveuserservice.model;

import com.bridgelabz.reactiveuserservice.dto.AddUserDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class User {

    @Id
    private String id;
    private String name;
    private String emailId;
    private String password;
    private String mobileNumber;
    private LocalDateTime localDateTime=LocalDateTime.now();

    public User(AddUserDto addUserDto) {
        this.emailId=addUserDto.emailId;
        this.name=addUserDto.name;
        this.password=addUserDto.password;
        this.mobileNumber=addUserDto.mobileNumber;
    }
}
