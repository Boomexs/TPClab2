package org.example;

public class Copy {
    // information expert, encapsulation
    private int copyId;
    private boolean isAvailable;
    private Book book;

    public Copy(int copyId, Book book) {
        this.copyId = copyId;
        this.isAvailable = true;
        this.book = book;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrow() {
        if (isAvailable) {
            this.isAvailable = false;
        } else {
            System.out.println("Copy is already borrowed.");
        }
    }

    public void returnCopy() {
        this.isAvailable = true;
    }

    public int getCopyId() {
        return copyId;
    }

    public Book getBook() {
        return book;
    }
}
