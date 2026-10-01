import java.util.*;

abstract class Room {
    String roomNo;
    double price;
    List<Reservation> reservations = new ArrayList<>();

    Room(String roomNo, double price) {
        this.roomNo = roomNo;
        this.price = price;
    }

    abstract double calculatePrice(int days);

    boolean available(String start, String end) {
        for (Reservation r : reservations) {
            if (!r.cancelled &&
                !(end.compareTo(r.start) <= 0 ||
                  start.compareTo(r.end) >= 0)) {
                return false;
            }
        }
        return true;
    }
}

class StandardRoom extends Room {
    StandardRoom(String roomNo) {
        super(roomNo, 100);
    }

    double calculatePrice(int days) {
        return price * days;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(String roomNo) {
        super(roomNo, 150);
    }

    double calculatePrice(int days) {
        return price * days;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Reservation {
    Customer customer;
    Room room;
    String start, end;
    boolean cancelled = false;

    Reservation(Customer customer, Room room,
                String start, String end) {
        this.customer = customer;
        this.room = room;
        this.start = start;
        this.end = end;
    }

    void confirm() {
        if (room.available(start, end)) {
            room.reservations.add(this);

            System.out.println("Reservation confirmed for " +
                    customer.name + ", Room " + room.roomNo +
                    " (" + start + "-" + end + ").");

            System.out.println("Price: $" + room.calculatePrice(2));
        } else {
            System.out.println("Room " + room.roomNo +
                    " is not available from " + start + " to " + end);
        }
    }

    void cancel() {
        cancelled = true;
        System.out.println("Reservation for " + customer.name +
                ", Room " + room.roomNo +
                " cancelled successfully.");
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {

        Room standard = new StandardRoom("101");
        Room deluxe = new DeluxeRoom("201");

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        Reservation r1 =
                new Reservation(a, standard, "Jan 1", "Jan 5");

        if (standard.available("Jan 1", "Jan 5"))
            System.out.println("Standard Room 101 is available from Jan 1 to Jan 5.");

        r1.confirm();

        Reservation r2 =
                new Reservation(b, standard, "Jan 3", "Jan 7");

        r2.confirm();

        r1.cancel();

        Reservation r3 =
                new Reservation(c, deluxe, "Feb 10", "Feb 12");

        r3.confirm();
    }
}