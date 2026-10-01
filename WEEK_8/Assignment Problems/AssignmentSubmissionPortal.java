abstract class Assignment {
    String title;
    int maxMarks;

    Assignment(String title, int maxMarks) {
        this.title = title;
        this.maxMarks = maxMarks;
    }

    abstract double applyPenalty(double marks, int lateDays);
}

class CodingAssignment extends Assignment {

    CodingAssignment(String title, int maxMarks) {
        super(title, maxMarks);
    }

    double applyPenalty(double marks, int lateDays) {
        return marks * Math.max(0, 1 - 0.10 * lateDays);
    }
}

class WrittenAssignment extends Assignment {

    WrittenAssignment(String title, int maxMarks) {
        super(title, maxMarks);
    }

    double applyPenalty(double marks, int lateDays) {
        return marks * Math.max(0, 1 - 0.20 * lateDays);
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Submission {
    Student student;
    Assignment assignment;
    String date;
    String status = "Submitted";
    int lateDays;

    Submission(Student student, Assignment assignment,
               String date, int lateDays) {

        this.student = student;
        this.assignment = assignment;
        this.date = date;
        this.lateDays = lateDays;

        System.out.println(student.name + "'s submission for '"
                + assignment.title + "' received (" +
                (lateDays == 0 ? "on time" : lateDays + " days late") + ").");

        System.out.println("Status: " + status);
    }

    void grade(double marks) {

        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade this submission.");
            return;
        }

        double finalMarks =
                assignment.applyPenalty(marks, lateDays);

        System.out.printf("%s graded: %.0f/%d%n",
                student.name, finalMarks, assignment.maxMarks);

        if (lateDays > 0) {
            System.out.printf("After late penalty: %.0f/%d%n",
                    finalMarks, assignment.maxMarks);
        }

        status = "Graded";
        System.out.println("Status: " + status);
    }

    void resubmit() {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '" +
                    assignment.title + "' has already been graded.");
        }
    }
}

public class AssignmentSubmissionPortal {
    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding =
                new CodingAssignment("Linked List Lab", 50);

        Assignment written =
                new WrittenAssignment("Design Essay", 50);

        Submission s1 =
                new Submission(asha, coding, "Mar 10", 0);

        Submission s2 =
                new Submission(ravi, written, "Mar 14", 2);

        s1.grade(45);
        s2.grade(40);

        s1.resubmit();
    }
}