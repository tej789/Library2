package com.example.library.controller;


import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bs;

    public BookController(BookService bs) {
        this.bs = bs;
    }

    @PostMapping("/book")
    public ResponseEntity<?> addBook(@RequestBody Book book) {

        Book x =bs.addBook(book);
        if(x==null){
            return new ResponseEntity<>("Invalid Book",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bs.addBook(book), HttpStatus.CREATED);
    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(bs.getBooks(), HttpStatus.OK);
    }

    @PutMapping("book/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id,
                                        @RequestBody Book book) {

        Book updatedBook = bs.update(id, book);

        if (updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        }

        return new ResponseEntity<>("Book Not Found", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {

        if (bs.delete(id)) {
            return new ResponseEntity<>("Book Deleted", HttpStatus.OK);
        }

        return new ResponseEntity<>("Book Not Found", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/book/title/{title}")
    public List<Book> getByTitle(@PathVariable String title) {
        return bs.getBooksByTitle(title);
    }

    @GetMapping("/book/price/{price}")
    public List<Book> getByPrice(@PathVariable Double price) {
        return bs.getBooksByPrice(price);
    }

    @GetMapping("/book/writer/{name}")
    public List<Book> getByWriter(@PathVariable String name) {
        return bs.getBooksByWriter(name);
    }
}