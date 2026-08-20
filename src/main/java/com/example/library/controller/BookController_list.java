package com.example.library.controller;

import com.example.library.service.*;
import com.example.library.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@PreAuthorize("hasAnyRole('WRITER', 'USER','ADMIN')")
@RestController
public class BookController_list {
private final BookService_List bs;

public BookController_list(BookService_List bs){
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
