public class Student {
    private int adminNumber;
    private String name;
    private Book[] books;

    public Student(int adminNumber, String name) {
        this.adminNumber = adminNumber;
        this.name = name;
        this.books = new Book[50];
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) { 
        this.name = name;
    }

    public int adminNumber() { 
        return this.adminNumber;
    }

    public void setAdminNumber(int adminNumber) { 
        this.adminNumber = adminNumber;
    }

    public Book[] getBorrowedBooks() {
        return this.books;
    }
}
