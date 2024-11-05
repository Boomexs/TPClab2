package org.example;

import java.util.ArrayList;
import java.util.List;

public class Book {
    // low coupling, information expert
    private String title;
    private String author;
    private List<Copy> copies;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.copies = new ArrayList<>();
    }

    public void addCopy(Copy copy) {
        this.copies.add(copy);
    }

    public String getTitle() {
        return title;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public String getAuthor() {
        return author;
    }
}
