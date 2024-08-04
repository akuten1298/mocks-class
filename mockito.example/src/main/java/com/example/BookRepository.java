package com.example;

public interface BookRepository {
    Book findBookById(int id);
    void save(Book book);
}