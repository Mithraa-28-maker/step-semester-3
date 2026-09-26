public class MembershipSettlement {

    static class GymMember {

        private static int counter = 2000;
        private static int membersEnrolled = 0;

        private final String membershipNumber;

        private int monthlyFee;
        private int feesPaid;
        private String paymentMode;

        public GymMember(int monthlyFee) {

            this.monthlyFee = monthlyFee;

            counter++;

            membershipNumber = "GYM-" + counter;

            membersEnrolled++;
        }

        public void payFee(int amount) {
            feesPaid += amount;
        }

        public void payFee(int amount, String mode) {

            paymentMode = mode;

            payFee(amount);
        }

        public int getFeesPaid() {
            return feesPaid;
        }

        public String getMembershipNumber() {
            return membershipNumber;
        }

        static boolean isValidReferralCode(String code) {

            if (code == null || code.length() != 4) {
                return false;
            }

            return code.charAt(0) == 'G'
                    && Character.isDigit(code.charAt(1))
                    && Character.isDigit(code.charAt(2))
                    && Character.isUpperCase(code.charAt(3));
        }

        static int getMembersEnrolled() {
            return membersEnrolled;
        }
    }

    static class GroupClassMember extends GymMember {

        private String className;

        public GroupClassMember(int monthlyFee, String className) {
            super(monthlyFee);
            this.className = className;
        }
    }

    static String processWeeklyCheckIn(GymMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (GymMember member : members) {

            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof GroupClassMember) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + group + " group | "
                + individual + " individual";
    }

    public static void main(String[] args) {

        GymMember m1 = new GymMember(1000);

        System.out.println(m1.getMembershipNumber());
        System.out.println(GymMember.getMembersEnrolled());

        System.out.println(GymMember.isValidReferralCode("G45B"));
        System.out.println(GymMember.isValidReferralCode("G4B"));
        System.out.println(GymMember.isValidReferralCode("X45B"));

        m1.payFee(500);
        m1.payFee(500, "UPI");

        System.out.println(m1.getFeesPaid());

        GymMember[] members = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };

        System.out.println(processWeeklyCheckIn(members));
    }
}