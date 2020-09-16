package com.bridgelabz.reactiveuserservice.service;

import com.bridgelabz.reactiveuserservice.dto.AddUserDto;
import com.bridgelabz.reactiveuserservice.dto.LoginDTO;
import com.bridgelabz.reactiveuserservice.dto.ResetPasswordDto;
import com.bridgelabz.reactiveuserservice.model.User;
import com.bridgelabz.reactiveuserservice.repository.UserRepository;
import com.bridgelabz.reactiveuserservice.service.implementation.UserServiceImplementation;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class UserServiceTest {


    private AddUserDto addUserDto;
    private LoginDTO loginDTO;
    private ResetPasswordDto resetPasswordDto;
    private User user;

    @InjectMocks
    UserServiceImplementation userServiceImplementation;

    @Mock
    UserRepository userRepository;



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
        this.user=new User(this.addUserDto);
        Mono<User> userMono=Mono.just(this.user);
        Mockito.when(userRepository.save(any())).thenReturn(userMono);
        Mockito.when(userRepository.findByEmailId(any())).thenReturn(false);

        userServiceImplementation.addUser(this.addUserDto)
                .subscribe(result -> Assertions.assertEquals("User Added.", result));

    }

    @Test
    public void givenInValidUserDetails_AlreadyPresent_whenAdded_ShouldReturnCorrectResponse() {

        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        this.user=new User(this.addUserDto);
        Mono<User> userMono=Mono.just(this.user);
        Mockito.when(userRepository.save(any())).thenReturn(userMono);
        Mockito.when(userRepository.findByEmailId(any())).thenReturn(true);

        userServiceImplementation.addUser(this.addUserDto)
                .subscribe(result -> Assertions.assertEquals("User Already Present.", result));

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
        Mockito.when(userRepository.findByEmailId(any())).thenReturn(true);

        userServiceImplementation.authenticateUser(this.loginDTO)
                .subscribe(result -> Assertions.assertEquals("User Authenticated.", result));

    }
    @Test
    public void givenInValidLoginDetails_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020");
        Mockito.when(userRepository.findByEmailId(any())).thenReturn(false);

        userServiceImplementation.authenticateUser(this.loginDTO)
                .subscribe(result -> Assertions.assertEquals("User Authentication Failed.", result));

    }


    /*
     * @author  Rohan Kadam
     * @date    16 September 2020
     * @purpose User Verification Service Test Case.
     * */

    @Test
    public void givenValidToken_whenVerified_shouldReturnCorrectResponse() {
        Mockito.when(userRepository.findByEmailId(any())).thenReturn(true);

        userServiceImplementation.verifyUser("token")
                .subscribe(result -> Assertions.assertEquals("User Verified.", result));

    }

    @Test
    public void givenInValidToken_whenVerified_shouldReturnCorrectResponse() {
        Mockito.when(userRepository.findByEmailId(any())).thenReturn(false);

        userServiceImplementation.verifyUser("token")
                .subscribe(result -> Assertions.assertEquals("User Verification Failed.", result));

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


    /*
     * @author  Rohan Kadam
     * @date    16 September 2020
     * @purpose Get all Users Test Case.
     * */
    @Test
    public void givenValidToken_whenCorrect_shouldReturnCorrectResponse(){

     userServiceImplementation.getAllUser("token")
                .collectList().subscribe(result->Assertions.assertEquals(2,result.size()));


    }

}
