package com.example.webapp;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllUsers {

    private List<User> allUsers;

    public AllUsers() {
        allUsers = new ArrayList<>();
        allUsers.add(new User("krisAnd","admin", "krister.andersson@med.lu.se", "Krister", "Andersson"));
        allUsers.add(new User("Test","admin", "krister.andersson@med.lu.se", "Hello", "helllo"));
        allUsers.add(new User("hello","admin", "krister.andersson@med.lu.se", "test", "test"));
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void addNewUser(User user){

        allUsers.add(user);
    }

    public void removeTempararyUser(){
        allUsers.remove(allUsers.size()-1);
    }

    public User getTempararyUser(){
        return allUsers.get(allUsers.size()-1);
    }
}
