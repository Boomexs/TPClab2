package org.example;

import java.util.ArrayList;
import java.util.List;

public class Reader {
    // information expert, low coupling
    private int readerId;
    private String name;
    private List<Copy> borrowedBooks;

    public Reader(int readerId, String name) {
        this.readerId = readerId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Copy copy) {
        if (copy.isAvailable()) {
            copy.borrow();
            this.borrowedBooks.add(copy);
        } else {
            System.out.println("The book is not available.");
        }
    }

    public void returnBook(Copy copy) {
        copy.returnCopy();
        this.borrowedBooks.remove(copy);
    }

    public List<Copy> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getName() {
        return name;
    }

    public int getReaderId() {
        return readerId;
    }
}
