package com.example.library.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.library.Exception.Response;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return "Hello " + e.getMessage();
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Response> BookNotInDB(
            NoSuchElementException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Response("Book Not Found", ex.getMessage()));
    }

   @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> InvalidBook(
            IllegalArgumentException e
    ){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("Invalid Book",e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Response>  InvalidJSON(
    MethodArgumentNotValidException e
   ){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("Invalid JSON Data",e.getMessage()));
    }

}


/*

package com.example.library.Exception;

import com.example.library.DTO.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Book not found
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            NoSuchElementException e) {

        return new ResponseEntity<>(
                new ErrorResponse(
                        "Book Not Found",
                        "The requested book was not found"
                ),
                HttpStatus.NOT_FOUND
        );
    }


    // 2. Validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException e) {

        return new ResponseEntity<>(
                new ErrorResponse(
                        "Validation Error",
                        "Invalid input"
                ),
                HttpStatus.BAD_REQUEST
        );
    }


    // 3. Invalid JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJson(
            HttpMessageNotReadableException e) {

        return new ResponseEntity<>(
                new ErrorResponse(
                        "Invalid JSON",
                        "Request body is not valid JSON"
                ),
                HttpStatus.BAD_REQUEST
        );
    }


    // 4. Any other error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherException(
            Exception e) {

        return new ResponseEntity<>(
                new ErrorResponse(
                        "Internal Server Error",
                        "Something went wrong"
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
*/
