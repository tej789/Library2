package com.example.library.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {
    @Id
    private int id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id")
    private Writer writer;

    @ManyToMany
    @JoinTable(
            name = "book_type",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private List<Type> types;

    private double price;

    public Book(int id, String title , Writer writer,double price) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.price = price;
    }

    public Book() {

    }


    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }

    public Writer getWriter(){
        return writer;
    }

    public List<Type> getTypes(){return types;}
    public double getPrice(){
        return price;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public void setTypes(List<Type> types){this.types =types;}

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        String x =  "\nID : " + id + "  Title : " + title + "  Writer: " + writer + "  Price : " + price;
        return x;
    }

}
