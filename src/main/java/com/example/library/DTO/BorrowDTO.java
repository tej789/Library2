package com.example.library.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class BorrowDTO {


    private int id;
    private int userID;
    private String username;
    private int bookId;
    private String bookTitle;
        private LocalDate borrowDate;
        private LocalDate returnDate;
        private String Status;


}
