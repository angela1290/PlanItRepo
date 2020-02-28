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

    @Test
    public void shouldFindUsernameAndPasswordByID() {
        User user = userRepository.findById(2L).get();

        Assertions.assertEquals("Thal", user.getUsername());
        Assertions.assertEquals("thal123", user.getPassword());
    }

    @Test
    public void shouldFindUsernameAndPasswordByUsername() {
        User user2 = userRepository.findUserByUsername("Thal");

        Assertions.assertEquals("Thal", user2.getUsername());
        Assertions.assertEquals("thal123", user2.getPassword());
    }





    

}
