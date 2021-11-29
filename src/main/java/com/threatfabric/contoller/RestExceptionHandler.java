package com.threatfabric.contoller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = { ConstraintViolationException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = getNullFields(ex);
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private String getNullFields(RuntimeException exception){
        Iterator<ConstraintViolation<?>> iterator = ((ConstraintViolationException)exception)
                .getConstraintViolations().iterator();
        StringBuilder emptyFields = new StringBuilder("bellow fields should not be empty \n ");
        while(iterator.hasNext()){
            String emptyField = iterator.next().getPropertyPath().toString();
            emptyFields.append(String.format("%s ",emptyField));
        }
        return emptyFields.toString();
    }

}
