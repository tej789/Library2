package com.example.library.service;

import com.example.library.component.Validation;
import com.example.library.model.Book;
import com.example.library.model.Type;
import com.example.library.repository.BookRepository;
import com.example.library.repository.TypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository br;
    private final TypeRepository tr;
 private final Validation v;

   public BookService(BookRepository br, Validation v,TypeRepository tr){
       this.br = br;
       this.v = v;
       this.tr = tr;
   }
//    public Book addBook(Book book){
//       if(v.check(book)) return      br.save(book);
//
//       return null;
//    }

    public List<Book> gb(){
       return br.findAll();
    }


    public Page<Book> gb_for_Pageable(Pageable pageable) {
        return br.findAll( pageable);
    }

    public Book gb_for_DTO() {
        return br.findById(5).orElseThrow();
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

    public List<Book> getBooksGreaterThan(Double price){
       return  br.findBookGreaterThan(price);
    }


    public List<Book> getBooksByTitle(String title) {
        return br.findByTitle(title);
    }

    public List<Book> getBooksByPrice(double price) {
        return br.findByPrice(price);
    }

    public List<Book> getBooksByWriter(String name) {
        return br.findByWriter_Name(name);
    }

    public Book addBook(Book book) {


        List<Type> types = book.getTypes();
        List<Type> list = new ArrayList<>();

        for (Type type : types) {
            Type exist = tr.findById(type.getId())
                    .orElseThrow();

            list.add(exist);
        }

        book.setTypes(list);

        return br.save(book);
    }

}
