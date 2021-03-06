package com.bridgelabz.reactiveuserservice.service;

import com.bridgelabz.reactiveuserservice.dto.AddUserDto;
import com.bridgelabz.reactiveuserservice.dto.LoginDTO;
import com.bridgelabz.reactiveuserservice.dto.ResetPasswordDto;
import com.bridgelabz.reactiveuserservice.model.User;
import com.bridgelabz.reactiveuserservice.repository.UserRepository;
import com.bridgelabz.reactiveuserservice.service.implementation.UserServiceImplementation;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

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

    @BeforeEach
    void setUp() {
        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");

    }

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
       Mockito.when(userRepository.findByEmailId(any())).thenReturn(Optional.empty());

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
        Mockito.when(userRepository.findByEmailId(any())).thenReturn(Optional.of(Mono.just(this.user)));

        userServiceImplementation.addUser(this.addUserDto)
                .subscribe(result -> Assertions.assertEquals("User Already Exist.", result));

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
        this.user=new User(this.addUserDto);
        Mono<User> userMono=Mono.just(this.user);
        Mockito.when(userRepository.save(any())).thenReturn(userMono);
        Mockito.when(userRepository.findByEmailId(this.loginDTO.emailId)).thenReturn(Optional.of(Mono.just(this.user)));


        userServiceImplementation.authenticateUser(this.loginDTO)
                .subscribe(result -> Assertions.assertEquals("User Authenticated.", result));

    }
    @Test
    public void givenInValidLoginDetails_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020");

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
        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        this.user=new User(this.addUserDto);
        Mono<User> userMono=Mono.just(this.user);
        Mockito.when(userRepository.save(any())).thenReturn(userMono);
        Mockito.when(userRepository.findByEmailId(any()))
                .thenReturn(Optional.of(Mono.just(this.user)));

        userServiceImplementation.verifyUser("token")
                .subscribe(result -> Assertions.assertEquals("User Verified.", result));

    }


    @Test
    public void givenInValidToken_whenVerified_shouldReturnCorrectResponse() {
          Mockito.when(userRepository.findByEmailId(any())).thenReturn(Optional.empty());

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
        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        this.user=new User(this.addUserDto);
        Mono<User> userMono=Mono.just(this.user);
        Mockito.when(userRepository.save(any())).thenReturn(userMono);

        Mockito.when(userRepository.findByEmailId(any()))
                .thenReturn(Optional.of(Mono.just(this.user)));
        userServiceImplementation.forgotPassword("rohan.kadam@gmail.com")
                .subscribe(result->Assertions.assertEquals("User Password Forgotten.",result));
    }
    @Test
    public void givenInValidEmailAddress_whenForgotten_shouldReturnCorrectResponse(){
        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        this.user=new User(this.addUserDto);
        Mono<User> userMono=Mono.just(this.user);
//        Mockito.when(userRepository.findByEmailId(any())).thenReturn(Optional.of(this.user));

        userServiceImplementation.forgotPassword("rohan.kadam@gmail.com")
                .subscribe(result->Assertions.assertEquals("Email Doesn't Exists.",result));
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
    public void givenValidToken_whenCorrect_shouldReturnCorrectUserList(){
        Mockito.when(userRepository.findAll()).thenReturn(Flux.empty());

        userServiceImplementation.getAllUser()
                .collectList().subscribe(result->Assertions.assertEquals(0,result.size()));

    }



}
