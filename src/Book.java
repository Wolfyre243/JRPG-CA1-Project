import java.util.ArrayList;

public class Book {
    private String bookTitle; 
    private String author; 
    private String ISBN; 
    private double price; 
    private String category;
    private boolean avaliableForLoan; 

    public Book(
        String bookTitle, 
        String author, 
        String ISBN, 
        double price, 
        String category, 
        boolean avaliableForLoan
    ) { 
        this.bookTitle = bookTitle; 
        this.author = author;
        this.ISBN = ISBN; 
        this.price = price; 
        this.category = category;
        this.avaliableForLoan = avaliableForLoan;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getAvaliableForLoan() {
        return avaliableForLoan;
    }
    public void setAvaliableForLoan(boolean avaliableForLoan) {
        this.avaliableForLoan = avaliableForLoan;
    }
}