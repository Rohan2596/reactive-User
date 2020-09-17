package com.bridgelabz.reactiveuserservice.controller;

import com.bridgelabz.reactiveuserservice.dto.AddUserDto;
import com.bridgelabz.reactiveuserservice.dto.LoginDTO;
import com.bridgelabz.reactiveuserservice.dto.ResetPasswordDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reactive/user")
public class UserController {

    /**
     * localhost Url
     * http://localhost:8081/reactive/user
     *
     */


    @PostMapping
    public Mono<String> userAdded( @Valid @RequestBody AddUserDto addUserDto){
       return Mono.just("User Added.");
    }

    @PostMapping("/auth")
    public Mono<String> userAuthenticated(@Valid @RequestBody LoginDTO loginDTO){
        return Mono.just("User Authenticated.");
    }

    @GetMapping("/verify/{token}")
    public Mono<String> userVerification(@PathVariable(name = "token") String token){
        if( token.isEmpty()){
            return Mono.just("In-valid User Token.");
        }
        return Mono.just("User Verified.");
    }

    @GetMapping("/forgot")
    public Mono<String> forgotPassword(@RequestParam String emailId){
        if(emailId.isEmpty()){
            return Mono.just("Email Address Doesn't Exists.");
        }

        return Mono.just("User Password Forgotten.");
    }

    @PostMapping("/reset")
    public Mono<String> resetPassword(@RequestBody @Valid ResetPasswordDto resetPasswordDto){
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
