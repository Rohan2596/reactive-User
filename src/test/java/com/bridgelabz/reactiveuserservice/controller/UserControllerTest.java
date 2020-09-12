package com.bridgelabz.reactiveuserservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest
public class UserControllerTest {


    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void givenValidUserDetails_whenAdded_shouldReturnCorrectResponse(){


   webTestClient.post().uri("/reactive/user")
                 .accept(MediaType.APPLICATION_JSON_UTF8)
                 .exchange()
                  .expectStatus().isOk()
                 .expectBody(String.class)
              .isEqualTo("User Added.");


    }
}
