abstract class Seat {
    String number;

    Seat(String number) {
        this.number = number;
    }

    abstract double getPrice();
}

class RegularSeat extends Seat {
    RegularSeat(String number) {
        super(number);
    }

    double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {
    PremiumSeat(String number) {
        super(number);
    }

    double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {
    ReclinerSeat(String number) {
        super(number);
    }

    double getPrice() {
        return 400;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Show {
    String time;
    java.util.ArrayList<String> booked =
            new java.util.ArrayList<>();

    Show(String time) {
        this.time = time;
    }

    boolean isAvailable(Seat seat) {
        return !booked.contains(seat.number);
    }

    void book(Seat seat) {
        booked.add(seat.number);
    }

    void release(Seat seat) {
        booked.remove(seat.number);
    }
}

class Booking {
    Customer customer;
    Show show;
    java.util.ArrayList<Seat> seats =
            new java.util.ArrayList<>();
    boolean cancelled = false;

    Booking(Customer customer, Show show) {
        this.customer = customer;
        this.show = show;
    }

    void addSeat(Seat seat) {
        if (seats.size() >= 6) {
            System.out.println("Maximum 6 seats allowed.");
            return;
        }

        if (!show.isAvailable(seat)) {
            System.out.println("Seat " + seat.number +
                    " is already booked for this show.");
            return;
        }

        seats.add(seat);
        show.book(seat);
    }

    void confirm() {
        System.out.print("Booking confirmed for " +
                customer.name + ": ");

        for (Seat s : seats)
            System.out.print(s.number + " ");

        System.out.printf("%nTotal: ₹%.2f%n", getTotal());
    }

    double getTotal() {
        double total = 0;

        for (Seat s : seats)
            total += s.getPrice();

        return total;
    }

    void cancel(boolean showStarted) {
        if (showStarted) {
            System.out.println("Cannot cancel after show starts.");
            return;
        }

        for (Seat s : seats)
            show.release(s);

        cancelled = true;

        System.out.println(customer.name +
                "'s booking cancelled.");

        System.out.println("Seats released.");
    }
}

public class CampusPremiereTicketCounter {
    public static void main(String[] args) {

        Show show = new Show("7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking b1 = new Booking(asha, show);

        b1.addSeat(new RegularSeat("A1"));
        b1.addSeat(new RegularSeat("A2"));
        b1.addSeat(new PremiumSeat("F5"));

        b1.confirm();

        Booking b2 = new Booking(ravi, show);

        b2.addSeat(new RegularSeat("A2"));
        b2.addSeat(new ReclinerSeat("R1"));

        b2.confirm();

        b1.cancel(false);

        Booking b3 = new Booking(neha, show);

        b3.addSeat(new RegularSeat("A2"));

        b3.confirm();
    }
}