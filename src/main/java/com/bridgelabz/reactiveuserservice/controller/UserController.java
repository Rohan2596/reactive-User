package com.bridgelabz.reactiveuserservice.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/auth")
    public Mono<String> userAuthenticated(){
        return Mono.just("User Authenticated.");
    }

    @GetMapping("/verify")
    public Mono<String> userVerification(){
        return Mono.just("User Verified.");
    }

    @GetMapping("/forgot")
    public Mono<String> forgotPassword(){
        return Mono.just("User Password Forgotten.");
    }

    @PostMapping("/reset")
    public Mono<String> resetPassword(){
        return Mono.just("User Password Updated.");
    }

}
