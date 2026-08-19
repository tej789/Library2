package com.example.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    private LocalDate borrowDate;
    private LocalDate returnDate;

    private String status;

//public void setUser(User user){
//    this.user = user;
//}


}


//

//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