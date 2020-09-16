package com.bridgelabz.reactiveuserservice.service.implementation;

import com.bridgelabz.reactiveuserservice.dto.AddUserDto;
import com.bridgelabz.reactiveuserservice.dto.LoginDTO;
import com.bridgelabz.reactiveuserservice.dto.ResetPasswordDto;
import com.bridgelabz.reactiveuserservice.model.User;
import com.bridgelabz.reactiveuserservice.repository.UserRepository;
import com.bridgelabz.reactiveuserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImplementation implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<String> addUser(AddUserDto addUserDto) {

        boolean userPresent=userRepository.findByEmailId(addUserDto.emailId);
      if (userPresent==false){
          userRepository.save(new User(addUserDto));
          return Mono.just("User Added.");
      }
        return Mono.just("User Already Present.");
    }

    @Override
    public Mono<String> authenticateUser(LoginDTO loginDTO) {
        boolean userPresent=userRepository.findByEmailId(loginDTO.emailId);
        if (userPresent==true){
            return Mono.just("User Authenticated.");
        }
        return Mono.just("User Authentication Failed.");
    }

    @Override
    public Mono<String> verifyUser(String token) {
        String emailId=token;
        boolean userPresent=userRepository.findByEmailId(emailId);
        if (userPresent==false){
            return Mono.just("User Verification Failed.");
        }
        return Mono.just("User Verified.");
    }

    @Override
    public Mono<String> forgotPassword(String emailId) {
        boolean userPresent=userRepository.findByEmailId(emailId);
        if (userPresent==false){
            return Mono.just("Email Doesn't Exists.");
        }
        return Mono.just("User Password Forgotten.");
    }

    @Override
    public Mono<String> resetPassword(ResetPasswordDto resetPasswordDto) {
        return Mono.just("User Password Reset.");
    }

    @Override
    public Flux<String> getAllUser(String token) {

        return Flux.just("Rohan Kadam", "Warren Buffet");

    }
}
