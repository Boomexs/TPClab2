package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryCLI {
    // Controller cuz cli, low coupling cuz you can remove it and the rest will still run and compile fine,
    // high cohesion cuz it only does user input and reading from lib
    public static void start(Library library) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Library System - Enter commands:");
        while (true) {
            System.out.print("\n> ");
            String command = scanner.nextLine().trim();

            List<String> parts = parseArguments(command);

            if (parts.isEmpty()) {
                continue;
            }

            String action = parts.get(0).toLowerCase();

            switch (action) {
                case "addbook":
                    // Command: addBook <title> <author> <numCopies>
                    if (parts.size() == 4) {
                        String title = parts.get(1);
                        String author = parts.get(2);
                        try {
                            int numCopies = Integer.parseInt(parts.get(3));
                            Book newBook = new Book(title, author);
                            for (int i = 1; i <= numCopies; i++) {
                                newBook.addCopy(new Copy(i, newBook));
                            }
                            library.addBook(newBook);
                            System.out.println("Book '" + title + "' by " + author + " added successfully!");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number of copies.");
                        }
                    } else {
                        System.out.println("Usage: addBook <title> <author> <numCopies>");
                    }
                    break;

                case "addreader":
                    // command: addReader <readerId> <name>
                    if (parts.size() == 3) {
                        try {
                            int readerId = Integer.parseInt(parts.get(1));
                            String name = parts.get(2);
                            Reader newReader = new Reader(readerId, name);
                            library.addReader(newReader);
                            System.out.println("Reader '" + name + "' added successfully!");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid reader ID.");
                        }
                    } else {
                        System.out.println("Usage: addReader <readerId> <name>");
                    }
                    break;

                case "addcopy":
                    // Command: addCopy <bookTitle> <numCopies>
                    if (parts.size() == 3) {
                        String bookTitle = parts.get(1);
                        try {
                            int numCopies = Integer.parseInt(parts.get(2));
                            library.addCopiesToBook(bookTitle, numCopies);
                            System.out.println("Added " + numCopies + " copies of '" + bookTitle + "'");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number of copies.");
                        }
                    } else {
                        System.out.println("Usage: addCopy <bookTitle> <numCopies>");
                    }
                    break;

                case "borrowbook":
                    // Command: borrowBook <readerId> <bookTitle>
                    if (parts.size() == 3) {
                        try {
                            int readerId = Integer.parseInt(parts.get(1));
                            String bookTitle = parts.get(2);
                            library.borrowBook(readerId, bookTitle);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid reader ID.");
                        }
                    } else {
                        System.out.println("Usage: borrowBook <readerId> <bookTitle>");
                    }
                    break;

                case "returnbook":
                    // Command: returnBook <readerId> <bookTitle>
                    if (parts.size() == 3) {
                        try {
                            int readerId = Integer.parseInt(parts.get(1));
                            String bookTitle = parts.get(2);
                            library.returnBook(readerId, bookTitle);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid reader ID.");
                        }
                    } else {
                        System.out.println("Usage: returnBook <readerId> <bookTitle>");
                    }
                    break;

                case "listavailablebooks":
                    // Command: listAvailableBooks
                    System.out.println("\nAvailable Books:");
                    for (Book book : library.getBooks()) {
                        for (Copy copy : book.getCopies()) {
                            if (copy.isAvailable()) {
                                System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + " (Copy ID: " + copy.getCopyId() + ")");
                            }
                        }
                    }
                    break;

                case "listborrowedbooks":
                    // Command: listBorrowedBooks <readerId>
                    if (parts.size() == 2) {
                        try {
                            int readerId = Integer.parseInt(parts.get(1));
                            Reader reader = library.findReaderById(readerId);
                            if (reader != null) {
                                System.out.println("\nBorrowed Books for Reader " + readerId + ":");
                                for (Copy copy : reader.getBorrowedBooks()) {
                                    System.out.println("- " + copy.getBook().getTitle() + " by " + copy.getBook().getAuthor() + " (Copy ID: " + copy.getCopyId() + ")");
                                }
                            } else {
                                System.out.println("Reader not found.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid reader ID.");
                        }
                    } else {
                        System.out.println("Usage: listBorrowedBooks <readerId>");
                    }
                    break;

                case "help":
                    // Command: help
                    printHelp();
                    break;

                case "exit":
                    // Command: exit
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Unknown command. Please use a valid command.");
            }
        }
    }

    // Print help message explaining each command
    private static void printHelp() {
        System.out.println("\nAvailable commands:");
        System.out.println(" addBook <title> <author> <numCopies>");
        System.out.println("   - Adds a book to the library with the specified title, author, and number of copies.");
        System.out.println(" addReader <readerId> <name>");
        System.out.println("   - Adds a new reader with the specified readerId and name.");
        System.out.println(" addCopy <bookTitle> <numCopies>");
        System.out.println("   - Adds a new copy to the specified book.");
        System.out.println(" borrowBook <readerId> <bookTitle>");
        System.out.println("   - Allows the specified reader to borrow the specified book.");
        System.out.println(" returnBook <readerId> <bookTitle>");
        System.out.println("   - Allows the specified reader to return the specified book.");
        System.out.println(" listAvailableBooks");
        System.out.println("   - Lists all books in the library that have available copies.");
        System.out.println(" listBorrowedBooks <readerId>");
        System.out.println("   - Lists all books borrowed by the specified reader.");
        System.out.println(" exit");
        System.out.println("   - Exits the system.");
    }

    // Helper method to parse command arguments, keeping quoted arguments intact
    private static List<String> parseArguments(String command) {
        List<String> arguments = new ArrayList<>();
        // Regular expression for matching quoted arguments or non-whitespace arguments
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");
        Matcher matcher = pattern.matcher(command);

        while (matcher.find()) {
            // If the match is a quoted argument, add it as a single argument
            if (matcher.group(1) != null) {
                arguments.add(matcher.group(1)); // The quoted text
            } else if (matcher.group(2) != null) {
                arguments.add(matcher.group(2)); // A single non-whitespace argument
            }
        }

        return arguments;
    }
}
