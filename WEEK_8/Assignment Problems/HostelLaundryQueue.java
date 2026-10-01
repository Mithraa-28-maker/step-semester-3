abstract class WashType {
    abstract int getDuration();
    abstract double getCharge();
}

class QuickWash extends WashType {
    int getDuration() {
        return 30;
    }

    double getCharge() {
        return 20;
    }
}

class NormalWash extends WashType {
    int getDuration() {
        return 45;
    }

    double getCharge() {
        return 30;
    }
}

class HeavyWash extends WashType {
    int getDuration() {
        return 60;
    }

    double getCharge() {
        return 45;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class WashingMachine {
    String id;
    private boolean free = true;

    WashingMachine(String id) {
        this.id = id;
    }

    boolean isFree() {
        return free;
    }

    void start() {
        free = false;
    }

    void complete() {
        free = true;
    }
}

class WashCycle {
    Student student;
    WashingMachine machine;
    WashType type;

    WashCycle(Student student, WashingMachine machine, WashType type) {
        this.student = student;
        this.machine = machine;
        this.type = type;
    }

    void startWash() {
        if (!machine.isFree()) {
            System.out.println("Machine " + machine.id + " is currently busy.");
            return;
        }

        machine.start();

        System.out.println(type.getClass().getSimpleName().replace("Wash", "")
                + " wash started on " + machine.id + " for "
                + student.name + " (" + type.getDuration() + " min).");

        System.out.printf("Charge: ₹%.2f%n", type.getCharge());
    }

    void completeWash() {
        machine.complete();
        System.out.println(machine.id + " cycle completed. "
                + machine.id + " is now free.");
    }
}

public class HostelLaundryQueue {
    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashCycle w1 = new WashCycle(asha, m1, new QuickWash());
        w1.startWash();

        WashCycle w2 = new WashCycle(ravi, m1, new HeavyWash());
        w2.startWash();

        WashCycle w3 = new WashCycle(ravi, m2, new HeavyWash());
        w3.startWash();

        w1.completeWash();

        WashCycle w4 = new WashCycle(neha, m1, new NormalWash());
        w4.startWash();
    }
}