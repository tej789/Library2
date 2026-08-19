package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow,Integer> {

    boolean existsByBookIdAndStatus(int bookId, String status);
    List<Borrow> findByUserIdAndStatus(int userId, String status);
    List<Borrow> findByUserId(int userId);

    int book(Book book);
}

