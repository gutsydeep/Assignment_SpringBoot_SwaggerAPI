package com.Subhadeep.Assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PetExceptionHandler {

    @ExceptionHandler(value = {PetNotFoundException.class})
    public ResponseEntity<Object> handlePetNotFoundException
            (PetNotFoundException petNotFoundException)
    {
        PetException petException = new PetException(
                petNotFoundException.getMessage(),
                petNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(petException, HttpStatus.NOT_FOUND);
    }
}
