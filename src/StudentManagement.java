import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class StudentManagement {
    private ArrayList<Student> studentStore;

    public StudentManagement() {
        this.studentStore = new ArrayList<Student>();
    }

    public void displayStudents() {
        String displayMsg = "";

        for (int i = 0; i < studentStore.size(); i++) {
            displayMsg += "Student " + (i + 1) + ":\n" +
                    "Admin #: " + studentStore.get(i).getAdminNumber() + "\n" +
                    "Name: " + studentStore.get(i).getName() + "\n" +
                    "\n";
        }
        ;

        JOptionPane.showMessageDialog(null, displayMsg, "All Students", JOptionPane.INFORMATION_MESSAGE);
    }

    public void searchForStudent(String searchTerm) {
        // TODO: Improve searching?
        for (int i = 0; i < this.studentStore.size(); i++) {
            if (this.studentStore.get(i).getName().equalsIgnoreCase(searchTerm)) {
                final String foundMsg = "Admin #: " + this.studentStore.get(i).getAdminNumber() + "\n" +
                        "Name: " + this.studentStore.get(i).getName();
                JOptionPane.showMessageDialog(null, foundMsg, "Search Result", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        // If not found
        JOptionPane.showMessageDialog(null,
                "Cannot find the student \"" + searchTerm + "\"!",
                "Student Not Found",
                JOptionPane.ERROR_MESSAGE);
        return;
    }

    // TODO: Add student method
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
            JOptionPane.showMessageDialog(
                null, 
                "Admin Number must begin with a \'p\'.", 
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        } else if (studAdminNumber.length() > 8) {
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
