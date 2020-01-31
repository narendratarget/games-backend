package com.games.app.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.games.app.dto.ExceptionResponse;
 
@SuppressWarnings({"unchecked","rawtypes"})
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        
        ExceptionResponse error=new ExceptionResponse();
        error.setErrorCode("100");
        error.setStatus("FAILED");
        error.setErrorDescription(ex.getMessage());
        error.setTxnId(ex.getClass().getSimpleName());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}