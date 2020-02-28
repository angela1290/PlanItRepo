package com.example.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class LoginValidator implements Validator {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean supports(Class aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors e) {
        //ValidationUtils.rejectIfEmpty(e, "username", "username.empty");

        User user = (User) object;

        if(user.getUsername() == null || user.getUsername().equals("")){
            e.rejectValue("username","username.empty");
        }

      if(!userRepository.existsUserByUsername(user.getUsername())) {
          e.rejectValue("username2", "wrong username");

      }


        if(user.getPassword() == null || user.getPassword().equals("") ){
            e.rejectValue("password", "incorrect");
        }

    }
}
