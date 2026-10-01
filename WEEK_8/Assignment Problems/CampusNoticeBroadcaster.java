import java.util.*;

interface NotificationChannel {
    void send(Student student, String message);
}

class EmailChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[Email → " +
                student.name + "] " + message);
    }
}

class SmsChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[SMS → " +
                student.name + "] " + message);
    }
}

class AppChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[App → " +
                student.name + "] " + message);
    }
}

class Student {
    String name;
    String department;
    ArrayList<NotificationChannel> channels =
            new ArrayList<>();

    Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }
}

class Notice {
    String title;
    ArrayList<String> departments;

    Notice(String title, String... departments) {
        this.title = title;
        this.departments =
                new ArrayList<>(Arrays.asList(departments));
    }

    boolean isValid() {
        return title != null &&
               !title.isBlank() &&
               !departments.isEmpty();
    }
}

class NoticeBoard {
    ArrayList<Student> students =
            new ArrayList<>();

    void addStudent(Student student) {
        students.add(student);
    }

    void postNotice(Notice notice) {

        if (!notice.isValid()) {
            System.out.println(
                    "Cannot post notice: At least one target department is required.");
            return;
        }

        System.out.println("Notice '" + notice.title +
                "' posted to " +
                String.join(", ", notice.departments) + ".");

        for (Student student : students) {

            if (notice.departments.contains(student.department)) {

                for (NotificationChannel channel :
                        student.channels) {

                    channel.send(student, notice.title);
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {
    public static void main(String[] args) {

        Student asha = new Student("Asha", "CSE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addChannel(new SmsChannel());

        NoticeBoard board = new NoticeBoard();

        board.addStudent(asha);
        board.addStudent(ravi);

        Notice n1 =
                new Notice("Lab Closed Tomorrow", "CSE");

        board.postNotice(n1);

        Notice n2 =
                new Notice("Fee Deadline Extended", "CSE", "ECE");

        board.postNotice(n2);

        Notice n3 =
                new Notice("Sports Day");

        board.postNotice(n3);
    }
}