package com.example.library.repository;
import com.example.library.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book,Integer>{

        List<Book> findByTitle(String Title);

        List<Book> findByPrice(Double price);

    List<Book> findByWriter_Name(String name);
}
