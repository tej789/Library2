package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Book book;

    private LocalDate borrowDate;
    private LocalDate returnDate;

    private String status;


}
//         this.engine = new GasolineEngine so instead oof this can we writer as
//       GasolineEngine engine = new GasolineEngine();


// so instead can  this.engine = new GasolineEngine ,can we write as  GasolineEngine e = new GasolineEngine();
//     this.engine = e;