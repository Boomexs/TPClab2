# 1. Use Case: Add Book

## Scenario:
### Title: Add a new book to the library.

### Actors: User (Library System User)

### Preconditions:

    The user is logged in and using the command-line interface of the library system.

### Trigger:

    The user enters the command addbook <title> <author> <numCopies>.

### Main Flow:

    The system prompts the user to enter the command.
    The user enters a valid command with a book title, author, and number of copies, e.g., addbook "The Great Gatsby" "F. Scott Fitzgerald" 5.
    The system parses the command and extracts the title, author, and number of copies.
    The system creates a new Book object with the given title and author.
    The system adds the specified number of copies to the new book.
    The system successfully adds the book to the library.
    The system displays a confirmation message:
    Book 'The Great Gatsby' by F. Scott Fitzgerald added successfully!

### Postconditions:

    A new book is added to the library's catalog with the specified number of copies.

Alternative Flows:

    If the user enters an invalid number of copies (e.g., addbook "Book Title" "Author" abc), the system will print:
    Invalid number of copies.
    If the user provides an incorrect number of arguments, the system will show the correct usage:
    Usage: addBook <title> <author> <numCopies>

# 2. Use Case: Add Reader

## Scenario:
### Title: Add a new reader to the library.

### Actors: User (Library System User)

### Preconditions:

    The user is logged in and using the command-line interface of the library system.

### Trigger:

    The user enters the command addreader <readerId> <name>.

### Main Flow:

    The system prompts the user to enter the command.
    The user enters a valid command with a reader ID and name, e.g., addreader 1001 "John Doe".
    The system parses the command and extracts the reader ID and name.
    The system creates a new Reader object with the given ID and name.
    The system successfully adds the new reader to the library.
    The system displays a confirmation message:
    Reader 'John Doe' added successfully!

### Postconditions:

    A new reader is added to the library system with the specified ID and name.

Alternative Flows:

    If the user enters an invalid reader ID (e.g., non-numeric), the system will print:
    Invalid reader ID.
    If the user provides an incorrect number of arguments, the system will show the correct usage:
    Usage: addReader <readerId> <name>

# 3. Use Case: Add Copies to Book

## Scenario:
### Title: Add new copies to an existing book.

### Actors: User (Library System User)

### Preconditions:

    The user is logged in and using the command-line interface of the library system.
    The book already exists in the library catalog.

### Trigger:

    The user enters the command addcopy <bookTitle> <numCopies>.

### Main Flow:

    The system prompts the user to enter the command.
    The user enters a valid command with a book title and number of copies, e.g., addcopy "The Great Gatsby" 3.
    The system searches the library's catalog for the specified book.
    The system adds the specified number of copies to the existing book.
    The system displays a confirmation message:
    Added 3 copies of 'The Great Gatsby'

### Postconditions:

    The specified book now has the new copies added to its catalog.

Alternative Flows:

    If the user provides an invalid number of copies, the system will print:
    Invalid number of copies.
    If the user enters a title for a non-existing book, the system will print a message indicating that the book was not found (depending on system behavior).

# 4. Use Case: Borrow Book

## Scenario:
### Title: Borrow a book from the library.

### Actors: User (Library System User)

### Preconditions:

    The user is logged in and using the command-line interface of the library system.
    The reader exists in the library system.
    The specified book is available for borrowing.

### Trigger:

    The user enters the command borrowbook <readerId> <bookTitle>.

### Main Flow:

    The system prompts the user to enter the command.
    The user enters a valid command with a reader ID and book title, e.g., borrowbook 1001 "The Great Gatsby".
    The system validates the reader ID and book title.
    The system checks if the book is available for borrowing.
    If the book is available, the system assigns the book to the reader and updates the book's status.
    The system displays a confirmation message:
    Reader 1001 successfully borrowed 'The Great Gatsby'.

### Postconditions:

    The reader now has a borrowed copy of the specified book.

Alternative Flows:

    If the reader ID is invalid, the system will print:
    Invalid reader ID.
    If the book is not available, the system will print:
    The book is not available for borrowing.

# 5. Use Case: Return Book

## Scenario:
### Title: Return a borrowed book to the library.

### Actors: User (Library System User)

### Preconditions:

    The user is logged in and using the command-line interface of the library system.
    The reader exists in the library system.
    The reader has borrowed the specified book.

### Trigger:

    The user enters the command returnbook <readerId> <bookTitle>.

### Main Flow:

    The system prompts the user to enter the command.
    The user enters a valid command with a reader ID and book title, e.g., returnbook 1001 "The Great Gatsby".
    The system validates the reader ID and book title.
    The system checks if the reader has borrowed the specified book.
    If the book was borrowed, the system returns the book and updates its status.
    The system displays a confirmation message:
    Reader 1001 successfully returned 'The Great Gatsby'.

### Postconditions:

    The borrowed book is returned, and its status is updated to available.

Alternative Flows:

    If the reader ID is invalid, the system will print:
    Invalid reader ID.
    If the reader did not borrow the specified book, the system will print:
    This book was not borrowed by the reader.

# 6. Use Case: List Available Books

## Scenario:
### Title: List all available books in the library.

### Actors: User (Library System User)

### Preconditions:

    The user is logged in and using the command-line interface of the library system.
    The library has books in its catalog.

### Trigger:

    The user enters the command listavailablebooks.

### Main Flow:

    The system prompts the user to enter the command.

    The user enters the command listavailablebooks.

    The system fetches all the books in the library and checks for available copies.

    The system displays a list of books that have available copies.

    Example output:
    Available Books:
    - The Great Gatsby by F. Scott Fitzgerald (Copy ID: 1)
    - 1984 by George Orwell (Copy ID: 2)

### Postconditions:

    A list of available books is displayed to the user.

Alternative Flows:

    If no books are available, the system will print:
    No books are available for borrowing.

# 7. Use Case: List Borrowed Books

## Scenario:
### Title: List all books borrowed by a specific reader.

### Actors: User (Library System User)

### Preconditions:

    The user is logged in and using the command-line interface of the library system.
    The reader exists and has borrowed books.

### Trigger:

    The user enters the command listborrowedbooks <readerId>.

### Main Flow:

    The system prompts the user to enter the command.

    The user enters the command with a reader ID, e.g., listborrowedbooks 1001.

    The system checks the reader's borrowed books.

    The system displays a list of books borrowed by the specified reader.

    Example output:
    Borrowed Books for Reader 1001:
    - The Great Gatsby by F. Scott Fitzgerald (Copy ID: 1)

### Postconditions:

    A list of borrowed books for the reader is displayed.

Alternative Flows:

    If the reader ID is invalid, the system will print:
    Reader not found.
    If the reader has not borrowed any books, the system will print:
    No borrowed books for Reader 1001.

# 8. Use Case: Get Help

## Scenario:
### Title: Get a list of available commands.

### Actors: User (Library System User)

### Preconditions:

    The user is logged in and using the command-line interface of the library system.

### Trigger:

    The user enters the command help.

### Main Flow:

    The user enters the command help.

    The system displays a list of all available commands with descriptions.

    Example output:

    Available commands:
    addBook <title> <author> <numCopies> - Adds a book to the library
    addReader <readerId> <name> - Adds a new reader
    addCopy <bookTitle> <numCopies> - Adds copies to an existing book
    borrowBook <readerId> <bookTitle> - Borrows a book
    returnBook <readerId> <bookTitle> - Returns a borrowed book
    listAvailableBooks - Lists available books
    listBorrowedBooks <readerId> - Lists borrowed books for a reader
    exit - Exits the program

### Postconditions:

    The user receives a list of available commands.

# 9. Use Case: Exit Program

## Scenario:
### Title: Exit the library system.

### Actors: User (Library System User)

### Preconditions:

    The user is logged in and using the command-line interface of the library system.

### Trigger:

    The user enters the command exit.

### Main Flow:

    The user enters the command exit.
    The system prints a confirmation message:
    Exiting the system...
    The system exits the program.

### Postconditions:

    The library system shuts down and the user is logged out.