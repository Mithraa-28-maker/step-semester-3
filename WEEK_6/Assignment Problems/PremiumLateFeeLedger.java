public class PremiumLateFeeLedger {

    static class GymMember {

        private int[] lateFeeHistory = new int[10];
        private int feeCount = 0;

        protected void chargeLateFee(int amount) {

            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }

        public int[] getLateFeeHistory() {

            int[] copy = new int[feeCount];

            for (int i = 0; i < feeCount; i++) {
                copy[i] = lateFeeHistory[i];
            }

            return copy;
        }

        public int getTotalLateFees() {

            int total = 0;

            for (int i = 0; i < feeCount; i++) {
                total += lateFeeHistory[i];
            }

            return total;
        }
    }

    static class PremiumMember extends GymMember {

        @Override
        protected void chargeLateFee(int amount) {
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {

        PremiumMember p = new PremiumMember();

        p.chargeLateFee(200);

        System.out.println(p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();

        history[0] = 999;

        int[] newHistory = p.getLateFeeHistory();

        for (int value : newHistory) {
            System.out.print(value + " ");
        }
    }
}