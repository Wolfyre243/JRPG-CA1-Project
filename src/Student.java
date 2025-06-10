import java.util.ArrayList;

public class Student {
    private int adminNumber;
    private String name;
    private ArrayList<Book> books;

    public Student(int adminNumber, String name) {
        this.adminNumber = adminNumber;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) { 
        this.name = name;
    }

    public int getAdminNumber() { 
        return this.adminNumber;
    }

    public void setAdminNumber(int adminNumber) { 
        this.adminNumber = adminNumber;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return this.books;
    }

    public int countBorrowedBooks() {
        return this.books.size();
    }
}
