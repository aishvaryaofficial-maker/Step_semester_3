package week8.practice;

import java.util.*;

interface IPaymentMethod {
    boolean pay(double amount);
    String getName();
}

class CreditCardPayment implements IPaymentMethod {
    public boolean pay(double amount) {
        return true;
    }

    public String getName() {
        return "Credit Card";
    }
}

class DigitalWalletPayment implements IPaymentMethod {
    public boolean pay(double amount) {
        return false;
    }

    public String getName() {
        return "Digital Wallet";
    }
}

class LineItem {
    String item;
    int quantity;
    double price;

    LineItem(String item, int quantity, double price) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    double total() {
        return quantity * price;
    }
}

class Order {
    ArrayList<LineItem> items = new ArrayList<>();

    void addItem(String name, int qty, double price) {
        items.add(new LineItem(name, qty, price));
        System.out.println("Added " + name + " (Qty " + qty + ")");
    }

    double total() {
        double sum = 0;
        for (LineItem i : items)
            sum += i.total();
        return sum;
    }

    void placeOrder(IPaymentMethod payment) {

        if (items.isEmpty()) {
            System.out.println(
                "Cannot place order: Order must contain at least one item.");
            return;
        }

        System.out.println("Order placed successfully.");

        if (payment.pay(total())) {
            System.out.println("Payment via " +
                    payment.getName() + " successful.");
            System.out.println("Order status: Paid.");
        } else {
            System.out.println("Payment via " +
                    payment.getName() + " failed.");
            System.out.println("Order status: Pending Payment.");
        }
    }
}

public class p5{
    public static void main(String[] args) {

        Order order1 = new Order();

        System.out.println("Order created.");

        order1.addItem("Pizza", 2, 10);
        order1.addItem("Soda", 1, 5);

        order1.placeOrder(new CreditCardPayment());

        Order order2 = new Order();

        System.out.println("\nOrder created.");

        order2.addItem("Burger", 1, 12);

        order2.placeOrder(new DigitalWalletPayment());
    }
}