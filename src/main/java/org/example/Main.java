package org.example;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("The Bible", "God");
        book1.addCopy(new Copy(1, book1));
        library.addBook(book1);

        Book book2 = new Book("Design Patterns", "O'Reily");
        book2.addCopy(new Copy(2, book2));
        library.addBook(book2);

        Reader reader1 = new Reader(1, "Marek");
        Reader reader2 = new Reader(2, "Kamil");
        library.addReader(reader1);
        library.addReader(reader2);

        LibraryCLI.start(library);
    }
}
