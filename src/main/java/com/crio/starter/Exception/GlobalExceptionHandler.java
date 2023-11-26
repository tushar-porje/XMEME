package com.crio.starter.Exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<Map<String,String>> noSuchElementException(NoSuchElementException ex){
        Map<String,String> map=new HashMap<>();
        map.put("error","this id is not present");
        return new ResponseEntity<Map<String,String>>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatePostException.class)
    ResponseEntity<Map<String,String>> duplicatePostException(DuplicatePostException ex){
        Map<String,String> map=new HashMap<>();
        map.put("Error", ex.getMessage());
        return new ResponseEntity<Map<String,String>>(map, HttpStatus.CONFLICT);
    }
}
