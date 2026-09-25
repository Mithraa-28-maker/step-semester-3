public class BookInventory {

    private int copiesTotal;
    private int copiesAvailable;

    // Constructor
    public BookInventory(int copiesTotal) {
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    // Checks out one copy
    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    // Checks in one copy
    public void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    // Returns number of available copies
    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public static void main(String[] args) {

        BookInventory b = new BookInventory(3);

        // Check out 4 times
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();   // Invalid - silently rejected

        System.out.println(b.getCopiesAvailable());

        // Check in 4 times
        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();    // Invalid - silently rejected

        System.out.println(b.getCopiesAvailable());
    }
}