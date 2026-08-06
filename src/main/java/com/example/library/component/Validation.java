package com.example.library.component;
import com.example.library.model.Book;
import org.springframework.stereotype.Component;

@Component
public class Validation {
    public Boolean check(Book book){

        return book.getTitle()!= null && book.getWriter() !=null;

    }


}
