package com.bridgelabz.reactiveuserservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive/user")
public class UserController {

    @PostMapping
    public Mono<String> userAdded(){
        return Mono.just("User Added.");
    }


}
