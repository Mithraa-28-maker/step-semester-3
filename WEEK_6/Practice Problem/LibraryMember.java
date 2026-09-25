public class LibraryMember {

    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    // Constructor
    public LibraryMember(String memberId, int borrowLimit) {

        if (memberId == null ||
            memberId.trim().isEmpty() ||
            memberId.length() < 4) {

            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    // Borrow a book
    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    // Return number of borrowed books
    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    // Batch enrollment
    public static String enrollBatch(String[] memberIds, int borrowLimit) {

        int enrolled = 0;
        int rejected = 0;

        for (int i = 0; i < memberIds.length; i++) {

            try {
                LibraryMember member =
                    new LibraryMember(memberIds[i], borrowLimit);

                enrolled++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Enrolled: " + enrolled +
               " | Rejected: " + rejected;
    }

    // StudentMember subclass
    static class StudentMember extends LibraryMember {

        private String course;

        public StudentMember(String memberId,
                              int borrowLimit,
                              String course) {

            super(memberId, borrowLimit);
            this.course = course;
        }
    }

    // Main method
    public static void main(String[] args) {

        // Test StudentMember
        StudentMember s =
            new StudentMember("STU10", 3, "CSE");

        s.borrowBook();
        s.borrowBook();

        System.out.println(s.getBooksBorrowed());

        // Test batch enrollment
        String[] ids = {
            "STU1",
            "LB1",
            "STU2",
            " ",
            "STU3"
        };

        System.out.println(
            enrollBatch(ids, 3)
        );
    }
}