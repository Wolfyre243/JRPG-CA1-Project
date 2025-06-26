import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StudentManagement {
    private ArrayList<Student> studentStore;

    private static SoundPlayer errorAudio = new SoundPlayer("error.wav");

    public StudentManagement() {
        this.studentStore = new ArrayList<Student>();
    }

    public ArrayList<Student> getStudentStore() {
        return this.studentStore;
    }

    public void displayStudents() {
        Object[][] rows = new Object[studentStore.size()][4];
        Object[] cols = { "Student", "Admin #", "Name", "Books Borrowed" };

        for (int i = 0; i < studentStore.size(); i++) {
            rows[i] = new Object[]{ 
                i+1, 
                studentStore.get(i).getAdminNumber(),
                studentStore.get(i).getName(),
                studentStore.get(i).getBorrowedBooks().size()
            };
        }

        JTable table = new JTable(rows, cols);

        JOptionPane.showMessageDialog(null, new JScrollPane(table), "All Students", JOptionPane.INFORMATION_MESSAGE);
    }

    public void searchForStudent(String searchTerm) {
        for (int i = 0; i < this.studentStore.size(); i++) {
            if (this.studentStore.get(i).getName().equalsIgnoreCase(searchTerm)) {
                final String foundMsg = "Admin #: " + this.studentStore.get(i).getAdminNumber() + "\n" +
                        "Name: " + this.studentStore.get(i).getName();
                JOptionPane.showMessageDialog(null, foundMsg, "Search Result", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        // If not found
        errorAudio.playSound();
        JOptionPane.showMessageDialog(null,
                "Cannot find the student \"" + searchTerm + "\"!",
                "Student Not Found",
                JOptionPane.ERROR_MESSAGE);
        return;
    }

    public void advancedSearchForStudent(String partialName) {
        Object[][] rows = new Object[studentStore.size()][4];
        Object[] cols = { "Student", "Admin #", "Name", "Books Borrowed" };

        boolean found = false;

        for (int i = 0; i < studentStore.size(); i++) {
            if (studentStore.get(i).getName().toLowerCase().contains(partialName.toLowerCase())) {
                found = true;
                rows[i] = new Object[]{ 
                    i+1, 
                    studentStore.get(i).getAdminNumber(),
                    studentStore.get(i).getName(),
                    studentStore.get(i).getBorrowedBooks().size()
                };
            }
        }

        if (found) {
            JTable table = new JTable(rows, cols);
            JOptionPane.showMessageDialog(
                null,
                new JScrollPane(table),
                "Search Results",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            errorAudio.playSound();
            JOptionPane.showMessageDialog(
                null,
                "No students found containing \"" + partialName + "\".",
                "No Results",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void addStudent() {
        final String dialogTitle = "Add new student";
        final String studAdminNumber = JOptionPane.showInputDialog(
                null,
                "Enter the new student's admin number:", dialogTitle,
                JOptionPane.QUESTION_MESSAGE);

        // If user pressed cancel, stop student creation
        if (studAdminNumber == null)
            return;

        // Validation of studAdminNumber
        if (studAdminNumber.charAt(0) != 'p') {
            errorAudio.playSound();
            JOptionPane.showMessageDialog(
                null, 
                "Admin Number must begin with a \'p\'.", 
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        } else if (studAdminNumber.length() > 8) {
            errorAudio.playSound();
            JOptionPane.showMessageDialog(
                null, 
                "Admin Number must be exactly 8 characters.", 
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        final int adminNo = Integer.parseInt(studAdminNumber.split("[p]")[1]);
        if (adminNo < 1300000 || adminNo > 2600000) {
            errorAudio.playSound();
            JOptionPane.showMessageDialog(
                null, 
                "Invalid admin number.", 
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        final String studName = JOptionPane.showInputDialog(
            null,
            "Enter the new student's name:", dialogTitle,
            JOptionPane.QUESTION_MESSAGE
        );

        // If user pressed cancel, stop student creation
        if (studName == null) return;

        final Pattern namePattern = Pattern.compile("^[a-zA-Z\s]+$");
        final Matcher matcher = namePattern.matcher(studName);

        if (!matcher.find()) {
            errorAudio.playSound();
            JOptionPane.showMessageDialog(
                null, 
                "Name can only contain letters and spaces.", 
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        studentStore.add(new Student(studAdminNumber, studName));

        JOptionPane.showMessageDialog(
            null, 
            "Student added successfully.", dialogTitle,
            JOptionPane.INFORMATION_MESSAGE
        );

    }

    public int getStudentCount() {
        return this.studentStore.size();
    }

    public void initialiseStudents(Student[] studentsArr) {
        for (int i = 0; i < studentsArr.length; i++) {
            this.studentStore.add(studentsArr[i]);
        }
    }
}
