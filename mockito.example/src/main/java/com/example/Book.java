package com.example;

public class Book {
    private int id;
    private String title;
    private boolean borrowed;

    public Book(int id, String title, boolean borrowed) {
        this.id = id;
        this.title = title;
        this.borrowed = borrowed;
    }   

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }   

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
}
