package com.example.library.repository;
import com.example.library.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

        List<Book> findByTitle(String Title);

        List<Book> findByPrice(Double price);





    List<Book> findByWriter_Name(String name);

    @Query("select b from Book b where b.price > :price")
    List<Book> findBookGreaterThan(@Param("price") Double price);
}
