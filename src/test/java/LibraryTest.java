package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }

    @Test
    void testAddBook() {
        Book book = new Book("Spring Framework", "Rod Johnson");
        library.addBook(book);

        // Assert that the book is in the library
        assertTrue(library.getBooks().contains(book), "Book should be added to the library");
    }

    @Test
    void testAddReader() {
        Reader reader = new Reader(1, "John Doe");
        library.addReader(reader);

        // Assert that the reader is in the library
        assertNotNull(library.findReaderById(1), "Reader should be added to the library");
    }

    @Test
    void testBorrowBook() {
        Book book = new Book("Spring Framework", "Rod Johnson");
        library.addBook(book);
        library.addCopiesToBook("Spring Framework",1);
        Reader reader = new Reader(1, "John Doe");
        library.addReader(reader);

        // Borrow the book
        library.borrowBook(1, "Spring Framework");

        // Assert that the book is available again
        assertFalse(library.getBooks().get(0).getCopies().get(0).isAvailable(), "The book should be marked as available after returning it");
    }

    @Test
    void testReturnBook() {
        Book book = new Book("Spring Framework", "Rod Johnson");
        library.addBook(book);
        library.addCopiesToBook("Spring Framework",1);
        Reader reader = new Reader(1, "John Doe");
        library.addReader(reader);

        // Borrow the book
        library.borrowBook(1, "Spring Framework");

        // Return the book
        library.returnBook(1, "Spring Framework");

        // Assert that the book is available again
        assertTrue(library.getBooks().get(0).getCopies().get(0).isAvailable(), "The book should be marked as available after returning it");
    }

    @Test
    void testAddCopyToBook() {
        Book book = new Book("Spring Framework", "Rod Johnson");
        library.addBook(book);

        // Add a copy to the book
        library.addCopiesToBook("Spring Framework", 3);

        assertEquals(3, book.getCopies().size(), "The book should have 3 copies");
    }
}

