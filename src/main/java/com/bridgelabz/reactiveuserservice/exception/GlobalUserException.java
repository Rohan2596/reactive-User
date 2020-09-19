package com.bridgelabz.reactiveuserservice.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalUserException {

    @ExceptionHandler(UserException.class)
    public Mono<String> handleCMSException(UserException userexception){

        return Mono.just(userexception.exceptiontypes.message);
    }
}
