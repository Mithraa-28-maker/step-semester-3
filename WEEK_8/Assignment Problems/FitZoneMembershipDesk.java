interface MembershipPlan {
    double calculateFee();
}

class MonthlyPlan implements MembershipPlan {

    public double calculateFee() {
        return 1000;
    }
}

class QuarterlyPlan implements MembershipPlan {

    public double calculateFee() {
        return 1000 * 3 * 0.90;
    }
}

class AnnualPlan implements MembershipPlan {

    public double calculateFee() {
        return 1000 * 12 * 0.75;
    }
}

class Member {
    String name;

    Member(String name) {
        this.name = name;
    }
}

class Membership {
    Member member;
    MembershipPlan plan;
    String status = "Active";

    Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;

        System.out.println("Membership created for " +
                member.name);

        System.out.printf("Fee: ₹%.2f%n", plan.calculateFee());
        System.out.println("Status: " + status);
    }

    void checkIn() {
        if (status.equals("Active")) {
            System.out.println(member.name +
                    " checked in successfully.");
        } else {
            System.out.println("Check-in denied: " +
                    member.name + "'s membership is " + status + ".");
        }
    }

    void freeze() {
        if (status.equals("Expired")) {
            System.out.println(
                    "Cannot freeze an Expired membership.");
        } else if (status.equals("Active")) {
            status = "Frozen";

            System.out.println(member.name +
                    "'s membership frozen.");
            System.out.println("Status: " + status);
        }
    }

    void unfreeze() {
        if (status.equals("Frozen")) {
            status = "Active";

            System.out.println(member.name +
                    "'s membership unfrozen.");
            System.out.println("Status: " + status);
        } else {
            System.out.println(
                    "Cannot unfreeze an " + status + " membership.");
        }
    }

    void expire() {
        status = "Expired";

        System.out.println(member.name +
                "'s membership expired.");
        System.out.println("Status: " + status);
    }
}

public class FitZoneMembershipDesk {
    public static void main(String[] args) {

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership m1 =
                new Membership(asha, new QuarterlyPlan());

        Membership m2 =
                new Membership(ravi, new MonthlyPlan());

        m1.checkIn();

        m1.freeze();

        m1.checkIn();

        m2.expire();

        m2.freeze();
    }
}