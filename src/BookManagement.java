import java.util.ArrayList;

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
                "Avaliability: " + bookStore.get(i).getAvaliableForLoan() + "\n" +
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
                    "Avaliability: " + this.bookStore.get(i).getAvaliableForLoan();

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

        // If user pressed cancel, stop student creation
        if (bookTitle == null) return;

        final String bookAuthor = JOptionPane.showInputDialog(
            null, 
            "Enter the new book author:", dialogTitle, 
            JOptionPane.QUESTION_MESSAGE
        );

        // If user pressed cancel, stop student creation
        if (bookAuthor == null) return;


        final String bookISBN = JOptionPane.showInputDialog(
            null, 
            "Enter the new book ISBN:", dialogTitle, 
            JOptionPane.QUESTION_MESSAGE
        );

        // If user pressed cancel, stop student creation
        if (bookISBN == null) return;

        final String bookPriceStr = JOptionPane.showInputDialog(
            null, 
            "Enter the new book price:", dialogTitle, 
            JOptionPane.QUESTION_MESSAGE
        );

        // If user pressed cancel, stop student creation
        if (bookPriceStr == null) return;

        double bookPrice = Double.parseDouble(bookPriceStr);

        final String bookCategory = JOptionPane.showInputDialog(
            null, 
            "Enter the new book category:", dialogTitle, 
            JOptionPane.QUESTION_MESSAGE
        );

        // If user pressed cancel, stop student creation
        if (bookCategory == null) return;

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



    public void initialiseBooks(Book[] bookArr) {
        for (int i = 0; i < bookArr.length; i++) {
            this.bookStore.add(bookArr[i]);
        }
    }
}



