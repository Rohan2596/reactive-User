package com.bridgelabz.reactiveuserservice.service;

import com.bridgelabz.reactiveuserservice.dto.AddUserDto;
import com.bridgelabz.reactiveuserservice.dto.LoginDTO;
import com.bridgelabz.reactiveuserservice.dto.ResetPasswordDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<String> addUser(AddUserDto addUserDto);
    Mono<String> authenticateUser(LoginDTO loginDTO);
    Mono<String> verifyUser(String token);
    Mono<String> forgotPassword(String emailId);
    Mono<String> resetPassword(ResetPasswordDto resetPasswordDto);
    Flux<String> getAllUser(String token);
}
