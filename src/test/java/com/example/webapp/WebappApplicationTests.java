package com.example.webapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebappApplicationTests {

    @Autowired
    private UserRepository userRepository;


    @Test
    void contextLoads() {
    }

    @Test
    public void shouldFindCorrectNumberOfUsers(){
        Assertions.assertEquals(4,userRepository.count());
    }

}
