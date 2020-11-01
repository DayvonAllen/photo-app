package com.example.userservice.exceptions;

import com.example.userservice.api.v1.domain.ErrorMessage;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserRestException {

        @ExceptionHandler(Exception.class)
        public String exception(Exception e){
            return e.getMessage();
        }

        @ExceptionHandler(UserException.class)
        public ResponseEntity<ErrorMessage> exception(UserException e){
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), LocalDateTime.now());
            return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
        }
}
