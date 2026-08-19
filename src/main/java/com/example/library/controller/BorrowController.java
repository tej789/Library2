package com.example.library.controller;


import com.example.library.model.Borrow;
import com.example.library.service.BorrowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowController {

    private final BorrowService bs;


    public BorrowController(BorrowService bs){
        this.bs = bs;
    }

public ResponseEntity<Borrow>  borrowBook(@RequestParam int userId,@RequestParam int bookId){
        Borrow r = bs.borrowBook(userId,bookId);
        return new ResponseEntity<>(r, HttpStatus.OK);
}



}
