import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class BookManagement {
    private ArrayList<Book> bookStore;

     public BookManagement() {
        this.bookStore = new ArrayList<Book>();
    }

    // displayBooks
    public void displayBooks() {
        String displayMsg = "";

        for (int i = 0; i < bookStore.size(); i++) {
            displayMsg +=
                "Book " + (i + 1) + ":\n" +
                "Book Title: " + bookStore.get(i).getBookTitle() + "\n" +
                "Name: " + bookStore.get(i).getAuthor() + "\n" +
                "Avaliability: " + bookStore.get(i).getAvailableForLoan() + "\n" +
                "\n";
        };

        JOptionPane.showMessageDialog(null, displayMsg, "All Books", JOptionPane.INFORMATION_MESSAGE);
    }

    // searchBookByTitle
    public void searchForBook(String searchTerm) {
        // TODO: Improve searching?
        for (int i = 0; i < this.bookStore.size(); i++) {
            if (this.bookStore.get(i).getBookTitle().equalsIgnoreCase(searchTerm)) {
                final String foundMsg = 
                    "Book: \n" +
                    "Book Title: " + this.bookStore.get(i).getBookTitle() + "\n" +
                    "Book Author: " + this.bookStore.get(i).getAuthor() + "\n" +
                    "Avaliability: " + this.bookStore.get(i).getAvailableForLoan();

                JOptionPane.showMessageDialog(null, foundMsg, "Search Result", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        // If not found
        JOptionPane.showMessageDialog(null, 
            "Cannot find the book \"" + searchTerm + "\"",
            "Book Not Found", 
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }
    // regular exp?

    // addBook
    public void addBook() {
        final String dialogTitle = "Add new book";
        final String bookTitle = JOptionPane.showInputDialog(
            null, 
            "Enter the new book title:", dialogTitle, 
            JOptionPane.QUESTION_MESSAGE
        );

        if (bookTitle == null) return;

        final Pattern titlePattern = Pattern.compile("^[a-zA-Z0-9'\s]+$");
        final Matcher titleMatcher = titlePattern.matcher(bookTitle);

        if (!titleMatcher.find()) {
            JOptionPane.showMessageDialog(
                null, 
                "Title can only contain letters, numbers and spaces.", 
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        final String bookAuthor = JOptionPane.showInputDialog(
            null, 
            "Enter the new book author:", dialogTitle, 
            JOptionPane.QUESTION_MESSAGE
        );

        if (bookAuthor == null) return;

        final Pattern authorPattern = Pattern.compile("^[a-zA-Z\s]+$");
        final Matcher authorMatcher = authorPattern.matcher(bookAuthor);

        if (!authorMatcher.find()) {
            JOptionPane.showMessageDialog(
                null, 
                "Author name can only contain letters and spaces.", 
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        final String bookISBN = JOptionPane.showInputDialog(
            null, 
            "Enter the new book ISBN:", dialogTitle, 
            JOptionPane.QUESTION_MESSAGE
        );

        if (bookISBN == null) return;

        final Pattern ISBNPattern = Pattern.compile("^[0-9]+$");
        final Matcher ISBNMatcher = ISBNPattern.matcher(bookISBN);

        if (!ISBNMatcher.find()) {
            JOptionPane.showMessageDialog(
                null, 
                "ISBN can only contain numbers.", 
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        final String bookPriceStr = JOptionPane.showInputDialog(
            null, 
            "Enter the new book price:", dialogTitle, 
            JOptionPane.QUESTION_MESSAGE
        );

        if (bookPriceStr == null) return;

        double bookPrice = Double.parseDouble(bookPriceStr);

        final String bookCategory = JOptionPane.showInputDialog(
            null, 
            "Enter the new book category:", dialogTitle, 
            JOptionPane.QUESTION_MESSAGE
        );

        // If user pressed cancel, stop student creation
        if (bookCategory == null) return;

        final Pattern categoryPattern = Pattern.compile("^[a-zA-Z\s]+$");
        final Matcher categoryMatcher = categoryPattern.matcher(bookCategory);

        if (!categoryMatcher.find()) {
            JOptionPane.showMessageDialog(
                null, 
                "Category can only contain letters and spaces.", 
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        boolean availableForLoan = true;

        bookStore.add(new Book(bookTitle, bookAuthor, bookISBN, bookPrice, bookCategory, availableForLoan));

        JOptionPane.showMessageDialog(null, "Book added successfully.", dialogTitle, JOptionPane.INFORMATION_MESSAGE);

    }

    public double getTotalbookCost() {
        double totalBookCost = 0.0;

        for (int i = 0; i < bookStore.size(); i++) {
        totalBookCost += bookStore.get(i).getPrice();
        }
        return totalBookCost;
    }

    public static void borrowBook(Student student, Book book) {
        // Check if the book is available for loan
        if (book.getAvailableForLoan()) {
            student.addBorrowedBook(book);
            book.setAvailableForLoan(false); // Mark the book as not available for loan

            JOptionPane.showMessageDialog(
                null,
                "Book '" + book.getBookTitle() + "' borrowed successfully by " + student.getName() + "(" + student.getAdminNumber() + ").",
                "Book Borrowed Successfully",
                JOptionPane.INFORMATION_MESSAGE
            );

        } else {
            // If the book is not available for loan, display an error message
            JOptionPane.showMessageDialog(
                null,
                "Sorry, you cannot borrow the book \'" + book.getBookTitle() + "\' at this time.",
                "Book Unavailable",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void returnBook(Student student, Book book) {
        // Check if the book is borrowed by the student
        if (student.getBorrowedBooks().size() == 0) {
            JOptionPane.showMessageDialog(
                null,
                "You have no books borrowed!",
                "Not Found",
                JOptionPane.ERROR_MESSAGE
            );

            return; 
        }

        if (student.getBorrowedBooks().contains(book)) {
            student.returnBorrowedBook(book);

            book.setAvailableForLoan(true);

            JOptionPane.showMessageDialog(
                null,
                "Book '" + book.getBookTitle() + "' returned successfully by " + student.getName() + "(" + student.getAdminNumber() + ").",
                "Book Returned Successfully",
                JOptionPane.INFORMATION_MESSAGE
            );

        } else {
            // If the book is not borrowed by the student, display an error message
            JOptionPane.showMessageDialog(
                null,
                "You cannot return the book \'" + book.getBookTitle() + "\' as it was never borrowed by you.",
                "Book Not Borrowed",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void borrowOrReturnBook(Student studentUser) {
        final String menuTitle = "Borrow or Return Books";
        final String menu = 
            "Logged in as: " + studentUser.getName() + " (" + studentUser.getAdminNumber() + ")\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. View Borrowed Books\n" +
            "2. Return a Book\n" +
            "3. Borrow a Book\n" +
            "4. Exit\n" +
            "\n";

        int userChoice = 4;

        do {
            try {
                final String option = JOptionPane.showInputDialog(null, menu, menuTitle, JOptionPane.INFORMATION_MESSAGE);
                if (option != null) {
                    userChoice = Integer.parseInt(option);
                } else {
                    userChoice = 4; // If user pressed cancel, auto exit
                }

                if (userChoice == 1) {
                    // View all user's borrowed books
                    String displayMsg = "";
                    ArrayList<Book> studentBooks = studentUser.getBorrowedBooks();

                    if (studentBooks.size() == 0) {
                        displayMsg = "You have no borrowed books!";
                    } else {
                        for (int i = 0; i < studentBooks.size(); i++) {
                            displayMsg += 
                                "Book " + (i + 1) + ":\n" +
                                "Title: " + studentBooks.get(i).getBookTitle() + "\n" +
                                "Author: " + studentBooks.get(i).getAuthor() + "\n" +
                                "ISBN: " + studentBooks.get(i).getISBN() + "\n" +
                                "\n";
                        }
                    }

                    JOptionPane.showMessageDialog(
                        null, 
                        displayMsg, menuTitle, 
                        JOptionPane.INFORMATION_MESSAGE
                    );

                } else if (userChoice == 2) {
                    // TODO: Ask for book ISBN and return the book
                    // Flaw: ISBN for books must then be unique in validation?
                    Book bookToReturn = null;

                    final String bookISBN = JOptionPane.showInputDialog(
                        null,
                        "ISBN of the book to return:", menuTitle, 
                        JOptionPane.QUESTION_MESSAGE
                    );

                    for (int i = 0; i < this.bookStore.size(); i++) {
                        if (this.bookStore.get(i).getISBN().equalsIgnoreCase(bookISBN)) {
                            bookToReturn = this.bookStore.get(i);
                        }
                    }

                    if (bookToReturn == null) {
                        JOptionPane.showMessageDialog(
                            null, 
                            "No book with ISBN " + bookISBN + " found!", menuTitle, 
                            JOptionPane.ERROR_MESSAGE
                        );
                    } else {
                        returnBook(studentUser, bookToReturn);
                    }

                } else if (userChoice == 3) {
                    // TODO: Ask for book ISBN and borrow the book
                    // Flaw: same as return
                    Book bookToBorrow = null;

                    final String bookISBN = JOptionPane.showInputDialog(
                        null,
                        "ISBN of the book to borrow:", menuTitle, 
                        JOptionPane.QUESTION_MESSAGE
                    );

                    for (int i = 0; i < this.bookStore.size(); i++) {
                        if (this.bookStore.get(i).getISBN().equalsIgnoreCase(bookISBN)) {
                            bookToBorrow = this.bookStore.get(i);
                        }
                    }

                    if (bookToBorrow == null) {
                        JOptionPane.showMessageDialog(
                            null, 
                            "No book with ISBN " + bookISBN + " found!", menuTitle, 
                            JOptionPane.ERROR_MESSAGE
                        );
                    } else {
                        borrowBook(studentUser, bookToBorrow);
                    }

                } else if (userChoice == 4) {
                    // exit
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid option from the menu.", menuTitle, JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                if (e.getClass() == NumberFormatException.class) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.", menuTitle, JOptionPane.ERROR_MESSAGE);
                }
            }
        } while (userChoice != 4);
    }

    public void initialiseBooks(Book[] bookArr) {
        for (int i = 0; i < bookArr.length; i++) {
            this.bookStore.add(bookArr[i]);
        }
    }
}



