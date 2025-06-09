public class StudentManagement {
    private Student[] studentStore;

    public StudentManagement() {
        this.studentStore = new Student[50];
    }

    public void displayStudents() {
        
    }

    public void initialiseStudents(Student[] studentsArr) {
        for (int i = 0; i < studentsArr.length; i++) {
            this.studentStore[i] = studentsArr[i];
        }
    }
}
