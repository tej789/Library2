package com.example.library.service;


import com.example.library.model.Borrow;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRepository;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {


    private final BorrowRepository br;
    private final BookRepository b_r;
    private final UserRepository ur;

    public BorrowService(BorrowRepository br, BookRepository b_r, UserRepository ur){
        this.br  = br;
        this.b_r = b_r;
        this.ur = ur;
    }


    public Borrow borrowBook(int userId , int bookId){
        if(br.existsByBookIdAndStatus(bookId,"Borrowed" )){
throw new RuntimeException("book Already borrowed");
        }

        Borrow b = new Borrow();

     b.setUser(ur.getReferenceById(userId));
     b.setBook(b_r.getReferenceById(bookId));
     b.setBorrowDate(LocalDate.now());
    b.setStatus("Borrowed");

    return br.save(b);
    }


    public Borrow returnBook(int userId, int bookId) {
        List<Borrow> records = br.findByUserIdAndStatus(userId, "BORROWED");

        Borrow record = records.stream()
                .filter(b -> b.getBook().getId()==bookId)
                .findFirst()
                .get();
        record.setStatus("RETURNED");
        record.setReturnDate(LocalDate.now());

        return br.save(record);
    }

}
