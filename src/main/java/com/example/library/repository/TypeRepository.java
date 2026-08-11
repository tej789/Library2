package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {


    @Query("SELECT b FROM Book b JOIN b.types t WHERE t.name = :name")
    List<Book> findBookByType(@Param("name") String name);

}
