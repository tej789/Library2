package com.example.library.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


      @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e) {

          log.error("error: {}", e.getMessage());
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body(new Response("Error",e.getMessage()));
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Response> BookNotInDB(
            NoSuchElementException e) {

          log.error("Not Found error :{}",e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Response("Not Found", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String InvalidValidation(
            MethodArgumentNotValidException e
    ) {

          log.warn("Correct the Data fields : {}",e.getMessage());
        return "Some Field is Invallid";

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> InvalidBook(
            IllegalArgumentException e
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("Invallid Data", e.getMessage()));
    }
}
