package com.bridgelabz.reactiveuserservice.service;

import com.bridgelabz.reactiveuserservice.dto.AddUserDto;
import com.bridgelabz.reactiveuserservice.service.implementation.UserServiceImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    private AddUserDto addUserDto;


    /*
    * @author  Rohan Kadam
    * @date    16 September 2020
    * @purpose User Addition Service Test Case
    * */

    @Test
    public void givenValidUserDetails_whenAdded_ShouldReturnCorrectResponse(){

        this.addUserDto = new AddUserDto("Rohan Kadam",
                "rohan.kadam@bridgelabz.com",
                "BridgeLabz@2020",
                "7890123456");
       userServiceImplementation.addUser(this.addUserDto)
               .subscribe(result->Assertions.assertEquals("User Added.",result));

    }

}
