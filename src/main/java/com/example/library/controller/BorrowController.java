package com.example.library.controller;


import com.example.library.DTO.BorrowDTO;
import com.example.library.model.Borrow;
import com.example.library.service.BorrowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.ReadPendingException;

@RestController
public class BorrowController {

    private final BorrowService bs;


    public BorrowController(BorrowService bs){
        this.bs = bs;
    }

@PostMapping("/borrow")
public ResponseEntity<Borrow>  borrowBook(@RequestParam int userId,@RequestParam int bookId){
        Borrow r = bs.borrowBook(userId,bookId);
        return new ResponseEntity<>(r, HttpStatus.OK);
}


//    public ResponseEntity<BorrowDTO> borrowBook(@RequestBody BorrowDTO borrowDTO){
//
//    }

@PostMapping("/return")
public ResponseEntity<Borrow> returnBook(@RequestParam int userId , @RequestParam int bookId){
        Borrow r = bs.returnBook(userId,bookId);
        return new ResponseEntity<>(r,HttpStatus.OK);

}
}
