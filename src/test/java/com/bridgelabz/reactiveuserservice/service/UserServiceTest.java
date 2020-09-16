package com.bridgelabz.reactiveuserservice.service;

import com.bridgelabz.reactiveuserservice.dto.AddUserDto;
import com.bridgelabz.reactiveuserservice.dto.LoginDTO;
import com.bridgelabz.reactiveuserservice.dto.ResetPasswordDto;
import com.bridgelabz.reactiveuserservice.service.implementation.UserServiceImplementation;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    private AddUserDto addUserDto;
    private LoginDTO loginDTO;
    private ResetPasswordDto resetPasswordDto;


    /*
     * @author  Rohan Kadam
     * @date    16 September 2020
     * @purpose User Addition Service Test Case.
     * */

    @Test
    public void givenValidUserDetails_whenAdded_ShouldReturnCorrectResponse() {

        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        userServiceImplementation.addUser(this.addUserDto)
                .subscribe(result -> Assertions.assertEquals("User Added.", result));

    }


    /*
     * @author  Rohan Kadam
     * @date    16 September 2020
     * @purpose User Authentication Service Test Case.
     * */

    @Test
    public void givenValidLoginDetails_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020");
        userServiceImplementation.authenticateUser(this.loginDTO)
                .subscribe(result -> Assertions.assertEquals("User Authenticated.", result));

    }


    /*
     * @author  Rohan Kadam
     * @date    16 September 2020
     * @purpose User Verification Service Test Case.
     * */

    @Test
    public void givenValidToken_whenVerified_shouldReturnCorrectResponse() {
        userServiceImplementation.verifyUser("token")
                .subscribe(result -> Assertions.assertEquals("User Verified.", result));

    }

    /*
    * @author  Rohan Kadam
    * @date    16 September 2020
    * @purpose User Password Forgotten  Test Case.
    * */

    @Test
    public void givenValidEmailAddress_whenForgotten_shouldReturnCorrectResponse(){
        userServiceImplementation.forgotPassword("rohan.kadam@gmail.com")
                .subscribe(result->Assertions.assertEquals("User Password Forgotten.",result));
    }

    /*
     * @author  Rohan Kadam
     * @date    16 September 2020
     * @purpose User Password Reset  Test Case.
     * */

    @Test
    public void givenValidResetPassword_whenResetPassword_shouldReturnCorrectResponse(){
        this.resetPasswordDto=new ResetPasswordDto("BridgeLabz@2020","BridgeLabz@2020");

        userServiceImplementation.resetPassword(this.resetPasswordDto)
                .subscribe(result->Assertions.assertEquals("User Password Reset.",result));
    }

}
