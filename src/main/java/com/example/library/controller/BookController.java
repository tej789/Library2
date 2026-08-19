package com.example.library.controller;

import com.example.library.DTO.BookDTO;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Slf4j
@RestController
public class BookController {

    private final BookService bs;

    public BookController(BookService bs) {
        this.bs = bs;
    }

    @PreAuthorize("hasRole('WRITER')")
    @PostMapping("/book")
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book) throws Exception {

        Book x =bs.addBook(book);
        if(x==null){
            return new ResponseEntity<>("Invalid Book",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(x, HttpStatus.CREATED);
    }


    @PreAuthorize("hasAnyRole('WRITER', 'user','ADMIN')")
    @GetMapping("/book")
    public ResponseEntity<List<Book>> getBooks() {

        log.info("All books");
        return new ResponseEntity<>(bs.gb(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('WRITER', 'user','ADMIN')")
    @GetMapping("/bookPageable")
    public Page<Book> getBooks(Pageable pageable) {
        return bs.gb_for_Pageable(pageable);
    }

    @PreAuthorize("hasAnyRole('WRITER', 'user','ADMIN')")
    @GetMapping("/bookDTO")
    public BookDTO GB(){
        Book book =  bs.gb_for_DTO();

        return new BookDTO(book.getTitle(),book.getPrice());
    }

//    @PreAuthorize("hasAnyRole('WRITER','ADMIN','user')")
//    @GetMapping("/bookDTO")
//    public List<BookDTO> GB() {
//        return bs.gb_for_DTO()
//                .stream()
//                .map(book -> new BookDTO(book.getTitle(), book.getPrice()))
//                .toList();
//    }



    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    @PutMapping("book/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id,
                                        @RequestBody Book book) {

        Book updatedBook = bs.update(id, book);

        if (updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        }

        return new ResponseEntity<>("Book Not Found", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    @DeleteMapping("book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {

        if (bs.delete(id)) {
            return new ResponseEntity<>("Book Deleted", HttpStatus.OK);
        }

        return new ResponseEntity<>("Book Not Found", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('WRITER','ADMIN','user')")
    @GetMapping("/book/priceGreaterThan/{price}")
    public List<Book> getBookGT(@PathVariable Double price)
    {
        return bs.getBooksGreaterThan(price);
    }

    @PreAuthorize("hasAnyRole('WRITER','ADMIN','user')")
    @GetMapping("/book/title/{title}")
    public List<Book> getByTitle(@PathVariable String title) {
        return bs.getBooksByTitle(title);
    }

    @PreAuthorize("hasAnyRole('WRITER','ADMIN','user')")
    @GetMapping("/book/price/{price}")
    public List<Book> getByPrice(@PathVariable Double price) {
        return bs.getBooksByPrice(price);
    }


    @PreAuthorize("hasAnyRole('WRITER','ADMIN','user')")
    @GetMapping("/book/writer/{name}")
    public List<Book> getByWriter(@PathVariable String name) {
        return bs.getBooksByWriter(name);
    }
}