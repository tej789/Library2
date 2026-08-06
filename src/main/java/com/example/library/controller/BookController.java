package com.example.library.controller;

import com.example.library.service.*;
import com.example.library.component.*;
import com.example.library.model.*;
import com.example.library.controller.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BookController {
private final BookService bs;

public BookController(BookService bs){
    this.bs = bs;
}

@PostMapping("/books")
    public ResponseEntity<String> addBook(@RequestBody Book book){
    if(bs.AddBook(book)){
        return new ResponseEntity<>("Book ADded", HttpStatus.CREATED);
    }
    return new ResponseEntity<>("Invalid Book",HttpStatus.BAD_REQUEST);
}

@GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
    return new ResponseEntity<>(bs.getBooks(),HttpStatus.OK);
}

@PutMapping("/books/{id}")
    public ResponseEntity<String> update(@PathVariable int id ,@RequestBody Book book){
    if(bs.update(id,book)){
        return new ResponseEntity<>("Updated",HttpStatus.OK);

    }
    return new ResponseEntity<>("Book Not found",HttpStatus.BAD_REQUEST);
}

 @DeleteMapping("/books/{id}")
  public ResponseEntity<String> delete(@PathVariable int id){
    if(bs.delete(id)){
        return new ResponseEntity<>("deleted Successfully",HttpStatus.OK);
    }
    return new ResponseEntity<>("Book Not Found",HttpStatus.BAD_REQUEST);
 }

}
