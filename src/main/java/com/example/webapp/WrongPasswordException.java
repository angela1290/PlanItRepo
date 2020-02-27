package com.example.webapp;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class WrongPasswordException extends Exception {

}
