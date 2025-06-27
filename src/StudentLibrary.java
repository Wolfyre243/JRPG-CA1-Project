/**
 * Admin Number: 2429634 & 2424093
 * Class: DIT/FT/2A/01
 * @author Junkai & Jayden
 */


import java.util.ArrayList;

import javax.swing.JOptionPane;

public class StudentLibrary {
    // Define initial student arr
    private static final Student[] students = {
        new Student("p2429123", "John Doe"),
        new Student("p2429124", "Jane Lim"),
        new Student("p2429125", "Mary Poh"),
        new Student("p2429126", "Chris Tan"),
        new Student("p2429127", "Kyle Ng"),
        new Student("p2429128", "Jack Neo"),
        new Student("p2429129", "Borris Toh"),
        new Student("p2429120", "Peter Pan")
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

    private static SoundPlayer errorAudio = new SoundPlayer("error.wav");

    private static void studentMenuCycle() {
        final String menuTitle = "Mini Libary System - Student Management";
        final String studentMenu = 
            "Enter your option:\n" +
            "\n" +
            "1. Display all students\n" +
            "2. Search student by name\n" +
            "3. Advanced Search \n" +
            "4. Add new student\n" +
            "5. Display total number of students\n" +
            "6. Exit\n" +
            "\n";  


        int menuChoice = 6;

        do {
            try {
                final String option = JOptionPane.showInputDialog(null, studentMenu, menuTitle, JOptionPane.INFORMATION_MESSAGE);
                if (option != null) {
                    menuChoice = Integer.parseInt(option);
                } else {
                    menuChoice = 6; // If user pressed cancel, auto exit
                }

                if (menuChoice == 1) {
                    // call display all students method
                    studentManagement.displayStudents();
                } else if (menuChoice == 2) {
                    // call search student by name method
                    final String searchName = JOptionPane.showInputDialog(null, "Enter the Student name to search:\n", "Input", JOptionPane.QUESTION_MESSAGE);
                    if (searchName != null) {
                        studentManagement.searchForStudent(searchName);
                    }

                } else if (menuChoice == 3) {
                    final String partialName = JOptionPane.showInputDialog(
                        null, 
                        "Enter part of the student name:", 
                        "Advanced Search", 
                        JOptionPane.QUESTION_MESSAGE
                        );
                    if (partialName != null) {
                        studentManagement.advancedSearchForStudent(partialName);
                    }

                } else if (menuChoice == 4) {
                    // call add new student
                    studentManagement.addStudent();

                } else if (menuChoice == 5) {
                    // call display count method
                    JOptionPane.showMessageDialog(null,
                        "Total number of students: " + studentManagement.getStudentCount(), 
                        "Total Number of Students", 
                        JOptionPane.INFORMATION_MESSAGE
                    );

                } else if (menuChoice == 6) {
                    // exit
                } else {
                    errorAudio.playSound();
                    JOptionPane.showMessageDialog(null, "Please enter a valid option from the student menu.", menuTitle, JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                errorAudio.playSound();
                if (e.getClass() == NumberFormatException.class) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.", menuTitle, JOptionPane.ERROR_MESSAGE);
                }
            }
        } while (menuChoice != 6);

        return;
    }

    private static void bookMenuCycle() {
        final String menuTitle = "Mini Libary System - Book Management";
        final String bookMenu = 
            "Enter your option:\n" +
            "\n" +
            "1. Display all books\n" +
            "2. Search book by title\n" +
            "3. Add new book\n" +
            "4. Display total books cost\n" +
            "5. Borrow/Return a Book\n" +
            "6. Exit\n" +
            "\n";


        int menuChoice = 6;

        do {
            try {
                final String option = JOptionPane.showInputDialog(null, bookMenu, menuTitle, JOptionPane.INFORMATION_MESSAGE);
                if (option != null) {
                    menuChoice = Integer.parseInt(option);
                } else {
                    menuChoice = 6; // If user pressed cancel, auto exit
                }

                if (menuChoice == 1) {
                    // call display all books method
                    bookManagement.displayBooks();
                } else if (menuChoice == 2) {
                    // call search book by title method
                    final String searchTitle = JOptionPane.showInputDialog(null, "Enter the Book name to search:\n", "Input", JOptionPane.QUESTION_MESSAGE);
                    if (searchTitle != null) {
                        bookManagement.searchForBook(searchTitle);
                    }
                } else if (menuChoice == 3) {
                    // call add new book
                    bookManagement.addBook();
                } else if (menuChoice == 4) {
                    // call display book cost method
                    JOptionPane.showMessageDialog(null,
                        "Total book cost is $" + bookManagement.getTotalbookCost(), 
                        "Total Book Cost", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } else if (menuChoice == 5) {
                    final String inputAdminNo = JOptionPane.showInputDialog(
                        null, 
                        "What is your admin number?", "Login", 
                        JOptionPane.QUESTION_MESSAGE
                    );

                    Student studentUser = null;
                    ArrayList<Student> studentStore = studentManagement.getStudentStore();
                    for (int i = 0; i < studentStore.size(); i++) {
                        if (studentStore.get(i).getAdminNumber().equals(inputAdminNo)) {
                            studentUser = studentStore.get(i);
                            break;
                        }
                    }
                    if (studentUser != null) {
                        bookManagement.borrowOrReturnBook(studentUser);
                    } else {
                        JOptionPane.showMessageDialog(
                            null, 
                            "Student with admin number " + inputAdminNo + " not found", "Student Not Found", 
                            JOptionPane.ERROR_MESSAGE
                        );
                    }

                } else if (menuChoice == 6) {
                    // exit
                } else {
                    errorAudio.playSound();
                    JOptionPane.showMessageDialog(null, "Please enter a valid option from the book menu.", menuTitle, JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                errorAudio.playSound();
                if (e.getClass() == NumberFormatException.class) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.", menuTitle, JOptionPane.ERROR_MESSAGE);
                }
            }
        } while (menuChoice != 6);

        return;
    }

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

        int userInput = 3;

        // Main code
        do {
            try {
                final String option = JOptionPane.showInputDialog(null, mainMenu, menuTitle, JOptionPane.INFORMATION_MESSAGE);
                if (option != null) {
                    userInput = Integer.parseInt(option);
                } else {
                    userInput = 3; // If user pressed cancel, auto exit
                }

                if (userInput == 1) {
                    // Call student management menu
                    studentMenuCycle();
                } else if (userInput == 2) {
                    // Call book management menu
                    bookMenuCycle();
                } else if (userInput == 3) {
                    JOptionPane.showMessageDialog(null, "Program terminated.\nThank you!", menuTitle, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    errorAudio.playSound();
                    JOptionPane.showMessageDialog(null, "Please enter a valid option from the menu.", menuTitle, JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                errorAudio.playSound();
                if (e.getClass() == NumberFormatException.class) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.", menuTitle, JOptionPane.ERROR_MESSAGE);
                }
            }
        } while (userInput != 3);

        return;
    }
}

