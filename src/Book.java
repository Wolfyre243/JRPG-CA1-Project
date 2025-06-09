public class Book {
    private String bookTitle; 
    private String author; 
    private String ISBN; 
    private double price; 
    private String category;
    private boolean avaliableForLoan; 

    public Book (
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
}