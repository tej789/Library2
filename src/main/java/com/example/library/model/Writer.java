package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Writer {
    @Id
    private int id;
    private String name;

    @OneToMany(
            mappedBy = "writer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Book> books;

    public Writer(){}

    public Writer(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id =id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
