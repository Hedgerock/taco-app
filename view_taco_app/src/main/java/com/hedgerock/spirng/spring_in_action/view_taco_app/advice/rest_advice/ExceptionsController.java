package com.hedgerock.spirng.spring_in_action.view_taco_app.advice.rest_advice;


import com.hedgerock.spirng.spring_in_action.view_taco_app.model.exceptions.ErrorDetails;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsController {


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorDetails>emptyResultData() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Can't be empty");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

}
