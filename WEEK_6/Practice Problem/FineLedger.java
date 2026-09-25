import java.util.Arrays;

public class FineLedger {

    static class LibraryMember {

        protected String memberId;
        protected int borrowLimit;

        private int[] fineHistory;
        private int fineCount;

        public LibraryMember(String memberId, int borrowLimit) {

            this.memberId = memberId;
            this.borrowLimit = borrowLimit;

            fineHistory = new int[10];
            fineCount = 0;
        }

        protected void chargeFine(int amount) {

            if (fineCount < fineHistory.length) {
                fineHistory[fineCount] = amount;
                fineCount++;
            }
        }

        public int[] getFineHistory() {

            return Arrays.copyOf(
                fineHistory,
                fineCount
            );
        }

        public int getTotalFine() {

            int total = 0;

            for (int i = 0; i < fineCount; i++) {
                total += fineHistory[i];
            }

            return total;
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
        protected void chargeFine(int amount) {

            // Student gets 50% discount
            super.chargeFine(amount / 2);
        }
    }

    public static void main(String[] args) {

        StudentMember s =
            new StudentMember(
                "STU5",
                3,
                "CSE"
            );

        s.chargeFine(100);

        System.out.println(
            s.getTotalFine()
        );

        int[] history = s.getFineHistory();

        // Trying to modify returned array
        history[0] = 999;

        System.out.println(
            Arrays.toString(
                s.getFineHistory()
            )
        );
    }
}