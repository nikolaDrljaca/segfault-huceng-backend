package com.segmentationfault.huceng.usecases.signup.controller;

import com.segmentationfault.huceng.exception.UsernameAlreadyExists;
import com.segmentationfault.huceng.util.ErrorWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UsernameAlreadyExistsAdvice {

    @ResponseBody
    @ExceptionHandler(UsernameAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorWrapper usernameAlreadyExitsHandler(UsernameAlreadyExists exception) {
        return new ErrorWrapper(409, exception.getMessage());
    }
}
