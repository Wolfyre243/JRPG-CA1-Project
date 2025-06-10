import java.util.ArrayList;

import javax.swing.JOptionPane;

public class StudentManagement {
    private ArrayList<Student> studentStore;

    public StudentManagement() {
        this.studentStore = new ArrayList<Student>();
    }

    public void displayStudents() {
        String displayMsg = "";

        for (int i = 0; i < studentStore.size(); i++) {
            displayMsg +=
                "Student " + (i + 1) + ":\n" +
                "Admin #: " + studentStore.get(i).getAdminNumber() + "\n" +
                "Name: " + studentStore.get(i).getName() + "\n" +
                "\n";
        };

        JOptionPane.showMessageDialog(null, displayMsg, "All Students", JOptionPane.INFORMATION_MESSAGE);
    }

    public void searchForStudent(String searchTerm) {
        // TODO: Improve searching?
        for (int i = 0; i < this.studentStore.size(); i++) {
            if (this.studentStore.get(i).getName().equalsIgnoreCase(searchTerm)) {
                final String foundMsg = 
                    "Admin #: " + this.studentStore.get(i).getAdminNumber() + "\n" +
                    "Name: " + this.studentStore.get(i).getName();
                JOptionPane.showMessageDialog(null, foundMsg, "Search Result", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        // If not found
        JOptionPane.showMessageDialog(null, 
            "Cannot find the student \"" + searchTerm + "\"!",
            "Student Not Found", 
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // TODO: Add student method

    public int getStudentCount() {
        return this.studentStore.size();
    }

    public void initialiseStudents(Student[] studentsArr) {
        for (int i = 0; i < studentsArr.length; i++) {
            this.studentStore.add(studentsArr[i]);
        }
    }
}
