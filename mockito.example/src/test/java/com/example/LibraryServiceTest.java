package com.example;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.argThat;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.answer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class LibraryServiceTest {
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private LibraryService libraryService;

    @Before
    public void setUp() {
        bookRepository = mock(BookRepository.class);
        userRepository = mock(UserRepository.class);
        libraryService = new LibraryService(bookRepository, userRepository);
    }

    @Test
    public void testBorrowBook() {
        Book book = new Book(1, "Mockito for Beginners", false);
        User user = new User(1, "John Doe");

        when(bookRepository.findBookById(1)).thenReturn(book);
        when(userRepository.findUserById(1)).thenReturn(user);

        libraryService.borrowBook(1, 1);

        verify(bookRepository).findBookById(1);
        verify(userRepository).findUserById(1);
        verify(bookRepository).save(book);
        assert(book.isBorrowed());
    }

    @Test
    public void testReturnBook() {
        Book book = new Book(1, "Mockito for Beginners", true);

        when(bookRepository.findBookById(1)).thenReturn(book);

        libraryService.returnBook(1);

        verify(bookRepository).findBookById(1);
        verify(bookRepository).save(book);
        assert(!book.isBorrowed());
    }

    @Test
    public void testBorrowBookAlreadyBorrowed() {
        Book book = new Book(1, "Mockito for Beginners", true);
        User user = new User(1, "John Doe");

        when(bookRepository.findBookById(1)).thenReturn(book);
        when(userRepository.findUserById(1)).thenReturn(user);

        libraryService.borrowBook(1, 1);

        verify(bookRepository).findBookById(1);
        verify(userRepository).findUserById(1);
        verify(bookRepository, never()).save(book);
    }

    @Test
    public void testReturnBookNotBorrowed() {
        Book book = new Book(1, "Mockito for Beginners", false);

        when(bookRepository.findBookById(1)).thenReturn(book);

        libraryService.returnBook(1);

        verify(bookRepository).findBookById(1);
        verify(bookRepository, never()).save(book);
    }
}
