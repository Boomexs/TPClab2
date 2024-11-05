package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library {
    //GRASP -> controller, information expert
    private List<Book> books;
    private List<Reader> readers;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public void borrowBook(int readerId, String bookTitle) {
        // Check if the book exists
        Book book = findBookByTitle(bookTitle);
        if (book == null) {
            System.out.println("Error: Book titled '" + bookTitle + "' does not exist.");
            return;
        }

        // Check if the reader exists
        Reader reader = findReaderById(readerId);
        if (reader == null) {
            System.out.println("Error: Reader with ID " + readerId + " does not exist.");
            return;
        }

        // Try to find an available copy of the book
        for (Copy copy : book.getCopies()) {
            if (copy.isAvailable()) {
                // If an available copy is found, borrow it
                reader.borrowBook(copy);
                System.out.println(reader.getName() + " successfully borrowed '" + bookTitle + "'.");
                return;
            }
        }

        // If no copies are available
        System.out.println("Error: No available copies of the book '" + bookTitle + "' at the moment.");
    }

    public void returnBook(int readerId, String bookTitle) {
        // Check if the book exists
        Book book = findBookByTitle(bookTitle);
        if (book == null) {
            System.out.println("Error: Book titled '" + bookTitle + "' does not exist.");
            return;
        }

        // Check if the reader exists
        Reader reader = findReaderById(readerId);
        if (reader == null) {
            System.out.println("Error: Reader with ID " + readerId + " does not exist.");
            return;
        }

        // Find the copy that the reader is returning
        for (Copy copy : reader.getBorrowedBooks()) {
            if (copy.getBook().equals(book)) {
                reader.returnBook(copy);
                System.out.println(reader.getName() + " successfully returned '" + bookTitle + "'.");
                return;
            }
        }

        // If the reader doesn't have the book
        System.out.println("Error: " + reader.getName() + " does not have a copy of '" + bookTitle + "' to return.");
    }

    public void addCopiesToBook(String title, int numberOfCopies) {
        Book book = findBookByTitle(title);

        if (book == null) {
            System.out.println("Book with title '" + title + "' not found.");
            return;
        }

        for (int i = 0; i < numberOfCopies; i++) {
            Copy newCopy = new Copy(book.getCopies().size() + 1, book);
            book.addCopy(newCopy); // Add the new copy to the book
        }

        System.out.println(numberOfCopies + " copies of '" + title + "' added successfully!");
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public Reader findReaderById(int id) {
        for (Reader reader : readers) {
            if (reader.getReaderId() == id) {
                return reader;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return books;
    }
}
