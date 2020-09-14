package com.bridgelabz.reactiveuserservice.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@WebFluxTest
public class UserControllerTest {


    @Autowired
    private WebTestClient webTestClient;

    /*
    *@author ROHAN KADAM
    * Purpose:User ADDITION/Register Test Cases For Negative and Positive use cases
    * @date 14 September 2020
    * */

    @Test
    public void givenValidUserDetails_whenAdded_shouldReturnCorrectResponse(){

        webTestClient.post().uri("/reactive/user")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("User Added.");

    }


    /*
     *@author ROHAN KADAM
     * Purpose:User Authentication/Login Test Cases For Negative and Positive use cases
     * @date 14 September 2020
     * */

    @Test
    public void givenValidUserDetails_whenAuthenticated_shouldReturnCorrectResponse(){

        webTestClient.post().uri("/reactive/user/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("User Authenticated.");

    }

    /*
     * @author ROHAN KADAM
     * Purpose:User Verification Test Cases For Negative and Positive use cases
     * @date 14 September 2020
     * */

    @Test
    public void givenValidUserToken_whenVerified_shouldReturnCorrectResponse(){
        webTestClient.get().uri("/reactive/user/verify")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("User Verified.");
    }


    /*
     * @author ROHAN KADAM
     * Purpose:User Forgot Password Test Cases For Negative and Positive use cases
     * @date 14 September 2020
     * */

    @Test
    public void givenValidUserToken_whenForgot_shouldReturnCorrectResponse(){
        webTestClient.get().uri("/reactive/user/forgot")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("User Password Forgotten.");
    }


    /*
     * @author ROHAN KADAM
     * Purpose:User  Password Changed/Updated Test Cases For Negative and Positive use cases
     * @date 14 September 2020
     * */

    @Test
    public void givenValidResetDetails_whenUpdated_shouldReturnCorrectResponse(){
        webTestClient.post().uri("/reactive/user/reset")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("User Password Updated.");
    }

    /*
     * @author ROHAN KADAM
     * Purpose:Get All User Test Cases For Negative and Positive use cases
     * @date 14 September 2020
     * */

    @Test
    public void givenValidInput_whenVerified_shouldReturnListOfAllUser(){
        List<String> list= new ArrayList<>();
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
