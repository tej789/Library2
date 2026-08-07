package com.example.library.service;

import com.example.library.component.Validation;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository br;
 private final Validation v;

   public BookService(BookRepository br, Validation v){
       this.br = br;
       this.v = v;
   }
    public Book addBook(Book book){
       if(v.check(book)) return      br.save(book);

       return null;
    }

    public List<Book> getBooks(){
      return br.findAll();
    }

    public Book update(int id, Book newBook) {

        Optional<Book> optionalBook = br.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();

            book.setTitle(newBook.getTitle());
            book.setWriter(newBook.getWriter());
            book.setPrice(newBook.getPrice());

            return br.save(book);
        }

        return null;
    }


    public Boolean delete(int id){
if(br.existsById(id)){
    br.deleteById(id);
    return true;
}
return false;
    }


    public List<Book> getBooksByTitle(String title) {
        return br.findByTitle(title);
    }

    public List<Book> getBooksByPrice(double price) {
        return br.findByPrice(price);
    }

    public List<Book> getBooksByWriter(String name) {
        return br.findByWriter_Name(name);
    }}
