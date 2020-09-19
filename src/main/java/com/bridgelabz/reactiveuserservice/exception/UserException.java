package com.bridgelabz.reactiveuserservice.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserException extends RuntimeException {

    public String message;
    public Exceptiontypes exceptiontypes;

    public enum Exceptiontypes{

        User_ALREADY_EXIST("User Already Exists.");

        Exceptiontypes(String message) {
            this.message=message;
        }
        public String message;
    };

    public UserException(String s, String message, Exceptiontypes exceptiontypes) {
        super(s);
        this.message = message;
        this.exceptiontypes = exceptiontypes;
    }




}
