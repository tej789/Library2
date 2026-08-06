package com.example.library.model;
public class Book {
    private int id;
    private String title;
    private String writer;
    private double price;

    public Book(int id, String title , String writer ,double price) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.price = price;
    }



    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }

    public String getWriter(){
        return writer;
    }
    public double getPrice(){
        return price;
    }
    @Override
    public String toString() {
        String x =  "\nID : " + id + "  Title : " + title + "  Writer: " + writer + "  Price : " + price;
        return x;
    }
}
