package com.bridgelabz.reactiveuserservice.controller;

import com.bridgelabz.reactiveuserservice.dto.AddUserDto;
import com.bridgelabz.reactiveuserservice.dto.LoginDTO;

import com.bridgelabz.reactiveuserservice.dto.ResetPasswordDto;
import com.bridgelabz.reactiveuserservice.model.User;
import com.bridgelabz.reactiveuserservice.repository.UserRepository;
import com.bridgelabz.reactiveuserservice.service.UserService;
import com.bridgelabz.reactiveuserservice.service.implementation.UserServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(SpringExtension.class)
@WebFluxTest(UserController.class)
@Import(UserServiceImplementation.class)
public class UserControllerTest {


    @Autowired
    private WebTestClient webTestClient;

    private AddUserDto addUserDto;
    private LoginDTO loginDTO;
    private ResetPasswordDto resetPasswordDto;

    @MockBean
    UserRepository userRepository;

    User user;

    /*
     *@author ROHAN KADAM
     * Purpose:User ADDITION/Register Test Cases For Negative and Positive use cases
     * @date 14 September 2020
     * */

    @Test
    public void givenValidUserDetails_whenAdded_shouldReturnCorrectResponse() {
        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        Mockito.when(this.userRepository.findByEmailId(any())).thenReturn(Optional.empty());
        this.user=new User(this.addUserDto);
        Mono<User> userMono=Mono.just(this.user);
        Mockito.when(userRepository.save(any())).thenReturn(userMono);
        this.webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("User Added.");

    }

    @Test
    public void givenINValidUserDetails_whenAdded_alreadyExist_shouldReturnCorrectResponse() {
        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        this.user=new User(this.addUserDto);
        Mono<User> userMono=Mono.just(this.user);
        Mockito.when(this.userRepository.findByEmailId(any())).thenReturn(Optional.of(Mono.just(this.user)));

        Mockito.when(userRepository.save(any())).thenReturn(userMono);
        this.webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("User Already Exist.");

    }


    /*
     * User Name
     * */

    @Test
    public void givenInValidUserDetails_Name_null_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto(null,
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest();


    }

    @Test
    public void givenInValidUserDetails_Name_Empty_whenAdded_shouldReturnCorrectResponse() {

        this.addUserDto = new AddUserDto("",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_Name_pattern_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("11234",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_Name_sizeMin_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("ad",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_Name_sizeMax_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("ad xdfdsfsdfsdsfsdsfddsfds sdfsdfsdfds dfsfs sdfsdfsdf esfsdfdsfdsfds sdfdsf",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }


    /*
     * User Email Address
     * */


    @Test
    public void givenInValidUserDetails_email_null_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("Rohan Kadam",
                null,
                "BridgeLabz@2020",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_email_Empty_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("ROhan Kadam",
                " ",
                "BridgeLabz@2020",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_email_pattern1_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("Rohan kadam",
                "rohan.bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_email_pattern2_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("ROhan kadam",
                "rohan.kadam@@@#.com",
                "BridgeLabz@2020",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_email_pattern3_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("ROhan Kadam",
                "rohan.kadam@bridgelabz@.@com",
                "BridgeLabz@2020",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }



    /*
     * User Password
     * */


    @Test
    public void givenInValidUserDetails_password_null_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                null,
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_password_Empty_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("ROhan Kadam",
                "rohan.kadam@bridgelabz.com",
                "",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_password_pattern1_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("Rohan kadam",
                "rohan.bridgelabz.com",
                "Qwert<>[]&&*",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_password_pattern2_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("ROhan kadam",
                "rohan.kadam@bridgelabz.com",
                "brider--**-",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_password_pattern3_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("ROhan Kadam",
                "rohan.kadam@bridgelabz.com.in",
                "BridgeLabz~---875",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_password_sizeMin_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("ROhan Kadam",
                "rohan.kadam@bridgelabz.com.in",
                "B@2020",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_password_sizeMax_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("ROhan Kadam",
                "rohan.kadam@bridgelabz.com.in",
                "BridgeLabz@2020dsfksnfsfnsdf cxvcxvcxv zxczxczxcsssdfsdf",
                "7890123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }


    /*
     * User  Mobile Number
     * */


    @Test
    public void givenInValidUserDetails_MobileNumber_null_whenAdded_shouldReturnCorrectResponse() {

        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                "Bridgelabz@2020",
                null);
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_MobileNumber_Empty_whenAdded_shouldReturnCorrectResponse() {

        this.addUserDto = new AddUserDto("ROhan Kadam",
                "rohan.kadam@bridgelabz.com",
                "Bridgelabz@2020",
                " ");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_MobileNumber_pattern1_whenAdded_shouldReturnCorrectResponse() {

        this.addUserDto = new AddUserDto("Rohan kadam",
                "rohan.bridgelabz.com",
                "Bridgelabz@2020",
                "7123qwetry");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_MobileNumber_pattern2_whenAdded_shouldReturnCorrectResponse() {

        this.addUserDto = new AddUserDto("ROhan kadam",
                "rohan.kadam@bridgelabz.com",
                "Bridgelabz@2020",
                "12345qwert");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_MobileNumber_pattern3_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("ROhan Kadam",
                "rohan.kadam@bridgelabz.com.in",
                "BridgeLabz@2020",
                "78o0123456");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_mobileNumber_sizeMin_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("ROhan Kadam",
                "rohan.kadam@bridgelabz.com.in",
                "Bridgelabz@2020",
                "7890");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidUserDetails_mobileNumber_sizeMax_whenAdded_shouldReturnCorrectResponse() {


        this.addUserDto = new AddUserDto("ROhan Kadam",
                "rohan.kadam@bridgelabz.com.in",
                "BridgeLabz@2020",
                "789012345613123");
        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.addUserDto))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    /*
     *@author ROHAN KADAM
     * Purpose:User Authentication/Login Test Cases For Negative and Positive use cases
     * @date 14 September 2020
     * */

    @Test
    public void givenValidLoginDetails_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan.kadam@bridgelabz.com", "Bridgelabz@2020");
        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        Mockito.when(this.userRepository.findByEmailId(any())).thenReturn(Optional.empty());
        this.user=new User(this.addUserDto);
        Mono<User> userMono=Mono.just(this.user);
        Mockito.when(userRepository.save(any())).thenReturn(userMono);
        Mockito.when(this.userRepository.findByEmailId(any())).thenReturn(Optional.of(Mono.just(this.user)));

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("User Authenticated.");

    }

    @Test
    public void givenValidLoginDetails_whenAuthenticated_DoesntExist_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan.kadam@bridgelabz.com", "Bridgelabz@2020");

        Mockito.when(this.userRepository.findByEmailId(any())).thenReturn(Optional.empty());

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("User Authentication Failed.");

    }


    /*
    * Login Details
    * Invalid Email Id.
    * */

    @Test
    public void givenInValidLoginDetails_emailID_null_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO(null, "Bridgelabz@2020");

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();


    }

    @Test
    public void givenInValidLoginDetails_emailID_empty_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("", "Bridgelabz@2020");

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidLoginDetails_emailID_pattern_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan25.kadam@bri@dgelabz.com", "Bridgelabz@2020");

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidLoginDetails_emailID_pattern1_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan.kadam@", "Bridgelabz@2020");

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidLoginDetails_emailID_pattern2_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan.kadam@bri123/*", "Bridgelabz@2020");

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }


    @Test
    public void givenInValidLoginDetails_password_null_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan.kadam@bridgelabz.com", null);

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();


    }

    @Test
    public void givenInValidLoginDetails_password_empty_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan.kadam@bridgelabz.com", "");

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidLoginDetails_password_pattern_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan25.kadam@bri@dgelabz.com", "Brisgee@4/*");

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidLoginDetails_password_pattern1_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan.kadam@bridgelabz.com", "!<>rerwwvsd");

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidLoginDetails_password_pattern2_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan.kadam@bridgelabz.com", "bridfe@?''");

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidLoginDetails_password_sizeMin_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan.kadam@bridgelabz.com", "B@20202");

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }

    @Test
    public void givenInValidLoginDetails_password_sizeMax_whenAuthenticated_shouldReturnCorrectResponse() {

        this.loginDTO = new LoginDTO("rohan.kadam@bridgelabz.com", "Bridge@2020weallare the best version dsfsdfdsf sdfdsfsd ");

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(this.loginDTO))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();

    }


    /*
     * @author ROHAN KADAM
     * Purpose:User Verification Test Cases For Negative and Positive use cases
     * @date 14 September 2020
     * */

    @Test
    public void givenValidUserToken_whenVerified_shouldReturnCorrectResponse() {
        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        Mockito.when(this.userRepository.findByEmailId(any())).thenReturn(Optional.empty());
        this.user=new User(this.addUserDto);
        Mono<User> userMono=Mono.just(this.user);
        Mockito.when(userRepository.save(any())).thenReturn(userMono);
        Mockito.when(this.userRepository.findByEmailId(any())).thenReturn(Optional.of(Mono.just(this.user)));


        webTestClient.get().uri("/reactive/user/verify/{token}", "token")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("User Verified.");
    }
    @Test
    public void givenValidUserToken_whenVerified_inValidEmail_shouldReturnCorrectResponse() {
        Mockito.when(this.userRepository.findByEmailId(any())).thenReturn(Optional.empty());

        webTestClient.get().uri("/reactive/user/verify/{token}", "token")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("User Verification Failed.");
    }

    @Test
    public void givenInValidUserToken_empty_whenVerified_shouldReturnCorrectResponse() {
        webTestClient.get().uri("/reactive/user/verify/{token}", "")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is4xxClientError();

    }


    /*
     * @author ROHAN KADAM
     * Purpose:User Forgot Password Test Cases For Negative and Positive use cases
     * @date 14 September 2020
     * */

    @Test
    public void givenValidUserEmailAddress_whenForgot_shouldReturnCorrectResponse() {
        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
        Mockito.when(this.userRepository.findByEmailId(any())).thenReturn(Optional.empty());
        this.user=new User(this.addUserDto);
        Mono<User> userMono=Mono.just(this.user);
        Mockito.when(userRepository.save(any())).thenReturn(userMono);
        Mockito.when(this.userRepository.findByEmailId(any())).thenReturn(Optional.of(Mono.just(this.user)));


        webTestClient.get().uri("/reactive/user/forgot/?emailId=" + "rohan.kadam@bridgelabz.com")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("User Password Forgotten.");
    }
    @Test
    public void givenValidUserEmailAddress_whenForgot_Failed_shouldReturnCorrectResponse() {

        Mockito.when(this.userRepository.findByEmailId(any())).thenReturn(Optional.empty());


        webTestClient.get().uri("/reactive/user/forgot/?emailId=" + "rohan.kadam@bridgelabz.com")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Email Doesn't Exists.");
    }

    @Test
    public void givenInValidUserEmail_whenForgot_shouldReturnCorrectResponse() {
        webTestClient.get().uri("/reactive/user/forgot/?emailId=" + "")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Email Address Doesn't Exists.");
    }

    @Test
    public void givenValidUserEmail_inValidUrl_whenForgot_shouldReturnCorrectResponse() {
        webTestClient.get().uri("/reactive/user/forgot/?emailI=" + "")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
    }

    /*
     * @author ROHAN KADAM
     * Purpose:User  Password Changed/Updated Test Cases For Negative and Positive use cases
     * @date 14 September 2020
     * */

    @Test
    public void givenValidResetDetails_whenUpdated_shouldReturnCorrectResponse() {
        this.resetPasswordDto = new ResetPasswordDto("Bridgelabz@2020", "Bridgelabz@2020");

        webTestClient.post().uri("/reactive/user/reset")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.resetPasswordDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("User Password Updated.");
    }

    @Test
    public void givenInValidResetDetails_null_whenUpdated_shouldReturnCorrectResponse() {
        this.resetPasswordDto = new ResetPasswordDto(null, "Bridgelabz@2020");

        webTestClient.post().uri("/reactive/user/reset")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.resetPasswordDto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void givenInValidResetDetails_empty_whenUpdated_shouldReturnCorrectResponse() {
        this.resetPasswordDto = new ResetPasswordDto(" ", "Bridgelabz@2020");

        webTestClient.post().uri("/reactive/user/reset")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.resetPasswordDto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void givenInValidResetDetails_pattern_whenUpdated_shouldReturnCorrectResponse() {
        this.resetPasswordDto = new ResetPasswordDto("B23<>[]sdfsf", "Bridgelabz@2020");

        webTestClient.post().uri("/reactive/user/reset")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.resetPasswordDto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void givenInValidResetDetails_pattern1_whenUpdated_shouldReturnCorrectResponse() {
        this.resetPasswordDto = new ResetPasswordDto("Bridg/*-[]-", "Bridgelabz@2020");

        webTestClient.post().uri("/reactive/user/reset")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.resetPasswordDto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void givenInValidResetDetails_pattern2_whenUpdated_shouldReturnCorrectResponse() {
        this.resetPasswordDto = new ResetPasswordDto("Bridgelabz@  2020", "Bridgelabz@2020");

        webTestClient.post().uri("/reactive/user/reset")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.resetPasswordDto))
                .exchange()
                .expectStatus().isBadRequest();
    }


    /*
     * Retype Password...
     *
     * */

    @Test
    public void givenInValidResetDetails_retype_null_whenUpdated_shouldReturnCorrectResponse() {
        this.resetPasswordDto = new ResetPasswordDto("Bridgelabz@2020", null);

        webTestClient.post().uri("/reactive/user/reset")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.resetPasswordDto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void givenInValidResetDetails_retype_empty_whenUpdated_shouldReturnCorrectResponse() {
        this.resetPasswordDto = new ResetPasswordDto("Bridgelabz@2020", " ");

        webTestClient.post().uri("/reactive/user/reset")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.resetPasswordDto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void givenInValidResetDetails_retype_pattern_whenUpdated_shouldReturnCorrectResponse() {
        this.resetPasswordDto = new ResetPasswordDto("Bridgelabz@2020", "B23<>[]sdfsf");

        webTestClient.post().uri("/reactive/user/reset")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.resetPasswordDto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void givenInValidResetDetails_retype_pattern1_whenUpdated_shouldReturnCorrectResponse() {
        this.resetPasswordDto = new ResetPasswordDto("Bridgelabz@2020", "Bridg/*-[]-");

        webTestClient.post().uri("/reactive/user/reset")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.resetPasswordDto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void givenInValidResetDetails_retype_pattern2_whenUpdated_shouldReturnCorrectResponse() {
        this.resetPasswordDto = new ResetPasswordDto("Bridgelabz@2020", "Bridgelabz@  2020");

        webTestClient.post().uri("/reactive/user/reset")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.resetPasswordDto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    /*
     * @author ROHAN KADAM
     * Purpose:Get All User Test Cases For Negative and Positive use cases
     * @date 14 September 2020
     * */

    @Test
    public void givenValidInput_whenVerified_shouldReturnListOfAllUser() {
        List<String> list = new ArrayList<>();
        list.add("John Doe");
        list.add("John Hopkins");
        webTestClient.get().uri("/reactive/user/all")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo(list.get(0));

    }


}
