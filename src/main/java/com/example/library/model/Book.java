package com.example.library.model;


import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    private int id;
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id")
    private Writer writer;

    private double price;

    public Book(int id, String title , Writer writer ,double price) {
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

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        String x =  "\nID : " + id + "  Title : " + title + "  Writer: " + writer + "  Price : " + price;
        return x;
    }

}
