package com.example;

public class LibraryService {
    private BookRepository bookRepository;
    private UserRepository userRepository;

    public LibraryService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public void borrowBook(int bookId, int userId) {
        Book book = bookRepository.findBookById(bookId);
        User user = userRepository.findUserById(userId);
        if (book != null && !book.isBorrowed() && user != null) {
            book.setBorrowed(true);
            bookRepository.save(book);
        }
    }

    public void returnBook(int bookId) {
        Book book = bookRepository.findBookById(bookId);
        if (book != null && book.isBorrowed()) {
            book.setBorrowed(false);
            bookRepository.save(book);
        }
    }
}
