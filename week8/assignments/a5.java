package week8.assignments;

import java.util.*;

interface PricingPlan {
    double price(double original);
}

class DayScholar implements PricingPlan {
    public double price(double original) {
        return original;
    }
}

class Hosteller implements PricingPlan {
    public double price(double original) {
        return original * 0.90;
    }
}

class Staff implements PricingPlan {
    public double price(double original) {
        return original * 0.80;
    }
}

class Transaction {
    double amount;

    Transaction(double amount) {
        this.amount = amount;
    }
}

class SmartCard {
    String id;
    private double balance = 0;
    private boolean blocked = false;

    private ArrayList<Transaction> transactions =
        new ArrayList<>();

    PricingPlan plan;

    SmartCard(String id, PricingPlan plan) {
        this.id = id;
        this.plan = plan;
    }

    void topUp(double amount) {

        if (blocked) {
            System.out.println("Top-up rejected: Card is blocked.");
            return;
        }

        if (amount < 100) {
            System.out.println("Top-up must be at least ₹100.");
            return;
        }

        if (balance + amount > 5000) {
            System.out.println("Top-up rejected: Maximum balance is ₹5000.");
            return;
        }

        balance += amount;
        transactions.add(new Transaction(amount));

        System.out.printf(
            "%s topped up with ₹%.2f. Balance: ₹%.2f%n",
            id, amount, balance);
    }

    double purchase(String item, double originalPrice) {

        if (blocked) {
            System.out.println("Purchase rejected: Card is blocked.");
            return -1;
        }

        double price = plan.price(originalPrice);

        if (price > balance) {
            System.out.printf(
                "Purchase failed: Insufficient balance " +
                "(required ₹%.2f, available ₹%.2f)%n",
                price, balance);
            return -1;
        }

        balance -= price;
        transactions.add(new Transaction(-price));

        System.out.printf(
            "%s purchased for ₹%.2f. Balance: ₹%.2f%n",
            item, price, balance);

        return price;
    }

    void refund(String item, double amount, boolean[] refunded) {

        if (refunded[0]) {
            System.out.println(
                "Refund rejected: " + item +
                " has already been refunded.");
            return;
        }

        balance += amount;
        transactions.add(new Transaction(amount));

        refunded[0] = true;

        System.out.printf(
            "Refund of ₹%.2f for %s processed. Balance: ₹%.2f%n",
            amount, item, balance);
    }

    void block() {
        blocked = true;
    }

    void unblock() {
        blocked = false;
    }

    void statement() {

        System.out.print("Mini-statement for " + id + ": ");

        double sum = 0;

        for (Transaction t : transactions) {
            System.out.printf("%+.2f, ", t.amount);
            sum += t.amount;
        }

        System.out.printf("= ₹%.2f%n", sum);
    }
}

public class a5 {
    public static void main(String[] args) {

        SmartCard card =
            new SmartCard("C-2045", new Hosteller());

        card.topUp(500);

        double veg =
            card.purchase("Veg Thali", 120);

        card.purchase("Cold Coffee", 60);

        card.purchase("Food Items", 400);

        boolean[] refunded = {false};

        card.refund("Veg Thali", veg, refunded);

        card.refund("Veg Thali", veg, refunded);

        card.statement();
    }
}