package com.example.webapp;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BudgetValidation implements Validator {


    @Override
    public boolean supports(Class aClass) {
        return Budget.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors e) {


        Budget budget = (Budget) object;



    }
}
