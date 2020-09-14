package com.bridgelabz.reactiveuserservice.controller;

import com.bridgelabz.reactiveuserservice.dto.AddUserDto;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reactive/user")
public class UserController {

    @PostMapping
    public Mono<String> userAdded( @Valid @RequestBody AddUserDto addUserDto){
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

    @GetMapping("/all")
    public Flux<String> getAllUser(){
        List<String> list= new ArrayList<>();
        list.add("John Doe");
        list.add("John Hopkins");
        return Flux.just(list.get(0));
    }

}
