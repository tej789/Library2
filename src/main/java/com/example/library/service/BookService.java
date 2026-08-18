package com.example.library.service;

import com.example.library.component.Validation;
import com.example.library.model.Book;
import com.example.library.model.Type;
import com.example.library.model.Writer;
import com.example.library.repository.BookRepository;
import com.example.library.repository.TypeRepository;
import com.example.library.repository.WriterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class BookService {

    private final BookRepository br;
    private final TypeRepository tr;
    private final Validation v;
    private final WriterRepository wr;

    public BookService(BookRepository br, Validation v, TypeRepository tr,WriterRepository wr) {
        this.br = br;
        this.v = v;
        this.tr = tr;
        this.wr = wr;
    }
//    public Book addBook(Book book){
//       if(v.check(book)) return      br.save(book);
//
//       return null;
//    }


    //    public Book update(int id, Book newBook) {
//
//        Book book = br.findById(id).orElse(null);
//
//        if (book != null) {
//
//            book.setTitle(newBook.getTitle());
//            book.setWriter(newBook.getWriter());
//            book.setPrice(newBook.getPrice());
//
//            return br.save(book);
//        }
//
//        return null;
//    }
    public List<Book> gb() {


        return br.findAll();
    }


    public Page<Book> gb_for_Pageable(Pageable pageable) {
        return br.findAll(pageable);
    }

    public Book gb_for_DTO() {
        return br.findById(12).orElseThrow();
    }


//    public List<Book>  gb_for_DTO() {
//        return br.findAll();
//    }

    public Boolean delete(int id) {
        if (br.existsById(id)) {
            br.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Book> getBooksGreaterThan(Double price) {
        return br.findBookGreaterThan(price);
    }


//    public List<Book> getBooksByTitle(String title) {
//        return br.findByTitle(title);
//    }

    public List<Book> getBooksByTitle(String title) {
        List<Book> book = br.findByTitle(title);
        if (book.isEmpty()) {
            throw new NoSuchElementException("No book found with this title");
        }
        return book;
    }




    public List<Book> getBooksByPrice(double price) {
        return br.findByPrice(price);
    }

    public List<Book> getBooksByWriter(String name) {
        return br.findByWriter_Name(name);
    }


    public Book addBook(Book book) throws Exception{
        if (book.getTitle() == null) {
            throw new IllegalArgumentException("Title is null");
        }
        Writer writer = wr.findById(book.getWriter().getId())
                .orElseThrow(() -> new NoSuchElementException("Writer not found"));

        book.setWriter(writer);
        List<Type> types = book.getTypes();
        List<Type> list = new ArrayList<>();

        for (Type type : types) {
            Type exist = tr.findById(type.getId())
                    .orElseThrow(()-> new Exception("Correct the type"));

            list.add(exist);
        }

        book.setTypes(list);

        return br.save(book);
    }


    public Book update(int id, Book newBook) {

        Book book = br.findById(id).orElse(null);

        if (book != null) {

            book.setTitle(newBook.getTitle());
            book.setWriter(newBook.getWriter());
            book.setPrice(newBook.getPrice());

            List<Type> types = newBook.getTypes();
            List<Type> list = new ArrayList<>();

            for (Type type : types) {
                Type exist = tr.findById(type.getId())
                        .orElseThrow();

                list.add(exist);
            }

            book.setTypes(list);

            return br.save(book);
        }

        return null;
    }
}

