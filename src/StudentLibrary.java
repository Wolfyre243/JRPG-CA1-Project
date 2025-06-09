import javax.swing.JOptionPane;

public class StudentLibrary {
    // Define initial student arr
    private static final Student[] students = {
        new Student(2429123, "John Doe"),
        new Student(2429124, "Jane Lim"),
        new Student(2429125, "Mary Poh"),
        new Student(2429126, "Chris Tan"),
        new Student(2429127, "Kyle Ng"),
        new Student(2429128, "Jack Neo"),
        new Student(2429129, "Borris Toh"),
        new Student(2429120, "Peter Pan")
    };

    // Define initial books arr
    private static final Book[] books = {
        new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 10.99, "Fiction", true),
        new Book("1984", "George Orwell", "9780451524935", 9.99, "Dystopian", true),
        new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084", 7.99, "Classic", true),
        new Book("The Catcher in the Rye", "J.D. Salinger", "9780316769488", 8.99, "Fiction", true),
        new Book("Pride and Prejudice", "Jane Austen", "9780141040349", 6.99, "Romance", true)
    };

    private static final StudentManagement studentManagement = new StudentManagement();
    private static final BookManagement bookManagement = new BookManagement();

    public static void main(String[] args) {
        // Initialise stores
        studentManagement.initialiseStudents(students);
        bookManagement.initialiseBooks(books);

        // Menu Text
        final String menuTitle = "Mini Library System";
        final String mainMenu = 
            "Enter your option:\n" +
            "\n" +
            "1. Student Management\n" +
            "2. Books in Library\n" +
            "3. Exit\n" +
            "\n"
        ;

        int userInput = 0;

        // Main code
        do {
            try {
                // TODO: [FIX] When user presses cancel, for some reason NumberFormatException is called => coalesce input value?
                userInput = Integer.parseInt(JOptionPane.showInputDialog(null, mainMenu, menuTitle, JOptionPane.INFORMATION_MESSAGE));
                
                if (userInput == 1) {
                    // Call student management menu
                    JOptionPane.showMessageDialog(null, "students menu", menuTitle, JOptionPane.INFORMATION_MESSAGE);
                } else if (userInput == 2) {
                    // Call book management menu
                    JOptionPane.showMessageDialog(null, "books menu", menuTitle, JOptionPane.INFORMATION_MESSAGE);
                } else if (userInput == 3) {
                    JOptionPane.showMessageDialog(null, "Program terminated.\nThank you!", menuTitle, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid option from the menu.", menuTitle, JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                if (e.getClass() == NumberFormatException.class) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.", menuTitle, JOptionPane.ERROR_MESSAGE);
                }
            }
        } while (userInput != 3);

        return;
    }
}

