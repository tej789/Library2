package com.example.library.service;

import org.springframework.stereotype.Service;
import com.example.library.component.*;
import com.example.library.model.Book;

@Service
public class BookService {

    private final WriterService ws;
    private final Validation v;


    public BookService(WriterService ws,Validation v) {
        this.ws = ws;
        this.v = v;
    }
        public void addBook(Book book){

            if(v.check(book)){
                System.out.println("Book Added");
            }else{
                System.out.println("Invalid Book");
            }


    }


}
