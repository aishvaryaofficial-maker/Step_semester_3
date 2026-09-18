package week6.practice;

class EventTicket {
    String attendeeId;
    double basePrice;
    double paid;

    EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid ID");
        }

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
    }

    void pay(double amount) {
        paid += amount;
    }

    double getBalanceDue() {
        return basePrice - paid;
    }

    void printTicket() {
        System.out.println("Standard Event Ticket | Balance Due: " + getBalanceDue());
    }

    static String registerBatch(String[] ids, double price) {
        int registered = 0;
        int rejected = 0;

        for (String id : ids) {
            try {
                new EventTicket(id, price);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}

class WorkshopTicket extends EventTicket {
    String track;

    WorkshopTicket(String id, double price, String track) {
        super(id, price);
        this.track = track;
    }

    @Override
    void printTicket() {
        System.out.println("Workshop Ticket | Track: " + track
                + " | Balance Due: " + getBalanceDue());
    }
}

public class Main {
    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");

        w.pay(500);

        System.out.println(w.getBalanceDue());

        String[] ids = {"STU1", "ST1", "STU2", " ", "STU3"};

        System.out.println(EventTicket.registerBatch(ids, 500));
    }
}