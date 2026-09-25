public class CirculationReport {

    static class LibraryMember {

        protected String memberId;
        protected int borrowLimit;
        protected int booksBorrowed;

        public LibraryMember(String memberId,
                              int borrowLimit) {

            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        public void displayInfo(StringBuilder sb) {

            sb.append(
                "General | Books: "
                + booksBorrowed
                + " | "
            );
        }

        public int getBooksBorrowed() {
            return booksBorrowed;
        }
    }

    static class StudentMember extends LibraryMember {

        private String course;

        public StudentMember(String memberId,
                              int borrowLimit,
                              String course) {

            super(memberId, borrowLimit);
            this.course = course;
        }

        @Override
        public void displayInfo(StringBuilder sb) {

            sb.append(
                "Student | Course: "
                + course
                + " | Books: "
                + booksBorrowed
                + " "
            );
        }

        public String getCourse() {
            return course;
        }
    }

    static String batchPrint(LibraryMember[] members) {

        StringBuilder report =
            new StringBuilder();

        for (LibraryMember member : members) {

            // Polymorphic method call
            member.displayInfo(report);

            // Downcasting only after instanceof check
            if (member instanceof StudentMember) {

                StudentMember student =
                    (StudentMember) member;

                report.append(
                    "[Course via downcast: "
                    + student.getCourse()
                    + "] | "
                );
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {

        LibraryMember general =
            new LibraryMember("LB5", 3);

        StudentMember student =
            new StudentMember(
                "STU6",
                3,
                "ECE"
            );

        LibraryMember[] members = {
            general,
            student
        };

        System.out.println(
            batchPrint(members)
        );
    }
}