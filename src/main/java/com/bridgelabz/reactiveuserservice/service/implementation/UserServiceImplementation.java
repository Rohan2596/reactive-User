package com.bridgelabz.reactiveuserservice.service.implementation;

import com.bridgelabz.reactiveuserservice.dto.AddUserDto;
import com.bridgelabz.reactiveuserservice.dto.LoginDTO;
import com.bridgelabz.reactiveuserservice.dto.ResetPassword;
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
        return null;
    }

    @Override
    public Mono<String> resetPassword(ResetPassword resetPassword) {
        return null;
    }

    @Override
    public Flux<String> getAllUser(String token) {
        return null;
    }
}
