import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Payment for Order successful.");
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Payment for Order failed.");
        return false;
    }
}

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Order {
    String id;
    List<Product> products = new ArrayList<>();
    String status = "Pending";

    Order(String id) {
        this.id = id;
    }

    void addProduct(Product p) {
        products.add(p);
    }

    void pay(PaymentMethod method) {

        if (products.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        System.out.println("Payment initiated for Order " + id);

        double total = 0;

        for (Product p : products)
            total += p.price;

        if (method.processPayment(total)) {
            status = "Paid";
        }

        System.out.println("Order status: " + status);
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }

    Order createOrder(String id) {
        System.out.println("Order created for " + name + ".");
        return new Order(id);
    }
}

public class PaymentProcessingSystem {
    public static void main(String[] args) {

        Customer x = new Customer("Customer X");

        Order orderX = x.createOrder("X");
        orderX.addProduct(new Product("Product A", 100));
        orderX.addProduct(new Product("Product B", 50));

        orderX.pay(new CreditCardPayment());

        Customer y = new Customer("Customer Y");
        Order orderY = y.createOrder("Y");

        orderY.pay(new CreditCardPayment());

        Customer z = new Customer("Customer Z");
        Order orderZ = z.createOrder("Z");

        orderZ.addProduct(new Product("Product C", 80));

        orderZ.pay(new PayPalPayment());
    }
}