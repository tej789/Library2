package com.example.library.service;

import org.springframework.stereotype.Service;
import com.example.library.component.*;
import com.example.library.model.Book;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final WriterService ws;
    private final Validation v;

    private final List<Book> books= new ArrayList<>();

    public BookService(WriterService ws,Validation v) {
        this.ws = ws;
        this.v = v;
    }
        public Boolean AddBook(Book book){

            if(!v.check(book)){
             return false;
            }else{
                books.add(book);
                return true;
            }

    }

    public List<Book> getBooks(){
        return books;
    }

    public boolean update(int id,Book book)
    {
        if(!v.check(book)){
            return false;
        }
        for(int i =0;i<books.size();i++)
        {
          if(books.get(i).getId() == id){
              books.set(i,book);
              return true;
          }
        }
        return false;
    }

    public Boolean delete(int id){
        for(int i = 0;i<books.size();i++){
            if(books.get(i).getId()==id){
                books.remove(i);
                return true;
            }
        }
        return false;
    }
}
