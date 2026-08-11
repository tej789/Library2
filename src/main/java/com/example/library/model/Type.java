package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

import static org.aspectj.apache.bcel.Constants.types;

@Entity
public class Type {

    @Id
    private int id;

    private String name;

    @ManyToMany(
           mappedBy = "types"
    )
    @JsonIgnore
    private List<Book> books;

    public Type(){}

    public Type(int id,String name){
        this.id =id;
        this.name = name;
    }
    public int getId(){
        return id;
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
        return "Type{" + "id=" + id + ", name='" + name + '\'' + '}';
    }


}
