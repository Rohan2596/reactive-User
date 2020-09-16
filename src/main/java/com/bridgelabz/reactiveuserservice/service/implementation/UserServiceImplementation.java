package com.bridgelabz.reactiveuserservice.service.implementation;

import com.bridgelabz.reactiveuserservice.dto.AddUserDto;
import com.bridgelabz.reactiveuserservice.dto.LoginDTO;
import com.bridgelabz.reactiveuserservice.dto.ResetPasswordDto;
import com.bridgelabz.reactiveuserservice.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImplementation implements UserService {


    @Override
    public Mono<String> addUser(AddUserDto addUserDto) {

        return Mono.just("User Added.");
    }

    @Override
    public Mono<String> authenticateUser(LoginDTO loginDTO) {

        return Mono.just("User Authenticated.");
    }

    @Override
    public Mono<String> verifyUser(String token) {

        return Mono.just("User Verified.");
    }

    @Override
    public Mono<String> forgotPassword(String emailId) {
        return Mono.just("User Password Forgotten.");
    }

    @Override
    public Mono<String> resetPassword(ResetPasswordDto resetPasswordDto) {
        return Mono.just("User Password Reset.");
    }

    @Override
    public Flux<String> getAllUser(String token) {
        return null;
    }
}
