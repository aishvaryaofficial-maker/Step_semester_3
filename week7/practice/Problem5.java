package week7.practice;

abstract class LibraryItem {
    private static int count = 1000;
    private final String itemId;

    LibraryItem() {
        count++;
        itemId = "LIB-" + count;
    }

    public abstract int getLoanPeriodDays();

    public String getItemId() {
        return itemId;
    }
}

interface Renewable {
    String renew();
}

interface Reservable {
    String reserve();
}

class Textbook extends LibraryItem
        implements Renewable, Reservable {

    private String title;

    Textbook(String title) {
        this.title = title;
    }

    public int getLoanPeriodDays() {
        return 14;
    }

    public String renew() {
        return title + " renewed";
    }

    public String reserve() {
        return title + " reserved";
    }
}

class Magazine extends LibraryItem
        implements Renewable {

    private String title;

    Magazine(String title) {
        this.title = title;
    }

    public int getLoanPeriodDays() {
        return 7;
    }

    public String renew() {
        return title + " renewed";
    }
}

class DigitalPass implements Renewable {
    private String resourceName;

    DigitalPass(String resourceName) {
        this.resourceName = resourceName;
    }

    public String renew() {
        return resourceName + " renewed";
    }
}

public class Problem5 {

    static void processCheckouts(LibraryItem[] items) {
        for (LibraryItem item : items) {
            System.out.println(
                item.getLoanPeriodDays()
            );
        }
    }

    static String reserveIfSupported(Object o) {
        if (o instanceof Reservable) {
            Reservable r = (Reservable) o;
            return r.reserve();
        }

        return "Reservation not supported";
    }

    public static void main(String[] args) {

        Textbook t =
            new Textbook("Java Fundamentals");

        Magazine m =
            new Magazine("Tech Monthly");

        DigitalPass d =
            new DigitalPass("Online Java Course");

        System.out.println(t.getLoanPeriodDays());
        System.out.println(t.renew());
        System.out.println(t.reserve());

        System.out.println(
            reserveIfSupported(m)
        );

        System.out.println(
            reserveIfSupported(d)
        );

        LibraryItem ref = t;

        System.out.println(
            reserveIfSupported(ref)
        );

        processCheckouts(
            new LibraryItem[]{t, m}
        );
    }
}