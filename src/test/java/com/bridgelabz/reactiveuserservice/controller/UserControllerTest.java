package com.bridgelabz.reactiveuserservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;


@ExtendWith(SpringExtension.class)
@WebFluxTest
public class UserControllerTest {


    @Autowired
    private WebTestClient webTestClient;

    /*
    *@author ROHAN KADAM
    * Purpose:User ADDITION Test Cases For Negative and Positive use cases
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
     * Purpose:User ADDITION Test Cases For Negative and Positive use cases
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
}
