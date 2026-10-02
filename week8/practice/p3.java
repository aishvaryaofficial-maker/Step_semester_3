package week8.practice;

import java.time.LocalDate;
import java.util.*;

abstract class Room {
    int number;
    boolean booked = false;

    Room(int number) {
        this.number = number;
    }

    abstract double price(int days);
}

class DeluxeRoom extends Room {
    DeluxeRoom(int number) {
        super(number);
    }

    double price(int days) {
        return days * 200;
    }
}

class StandardRoom extends Room {
    StandardRoom(int number) {
        super(number);
    }

    double price(int days) {
        return days * 150;
    }
}

class Reservation {
    Room room;
    LocalDate start, end;
    boolean active = true;

    Reservation(Room room, LocalDate start, LocalDate end) {
        this.room = room;
        this.start = start;
        this.end = end;
    }

    boolean overlaps(LocalDate s, LocalDate e) {
        return start.isBefore(e) && s.isBefore(end);
    }
}

class Hotel {
    ArrayList<Reservation> reservations = new ArrayList<>();

    void book(Room room, LocalDate start, LocalDate end) {

        for (Reservation r : reservations) {
            if (r.active && r.room == room &&
                r.overlaps(start, end)) {
                System.out.println("Booking failed: Room " +
                        room.number + " is not available.");
                return;
            }
        }

        Reservation r = new Reservation(room, start, end);
        reservations.add(r);

        int days = (int)(end.toEpochDay() - start.toEpochDay());

        System.out.println("Room " + room.number + " booked.");
        System.out.printf("Total price: $%.2f%n", room.price(days));
    }

    void cancel(Room room) {
        for (Reservation r : reservations) {
            if (r.room == room && r.active) {
                r.active = false;
                System.out.println("Reservation for Room " +
                        room.number + " cancelled successfully.");
                return;
            }
        }
    }
}

public class p3
 {
    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        Room deluxe = new DeluxeRoom(101);
        Room standard = new StandardRoom(205);

        LocalDate d1 = LocalDate.of(2024, 12, 1);
        LocalDate d2 = LocalDate.of(2024, 12, 5);
        LocalDate d3 = LocalDate.of(2024, 12, 3);
        LocalDate d4 = LocalDate.of(2024, 12, 7);

        hotel.book(deluxe, d1, d2);
        hotel.book(standard, d3, d4);

        hotel.book(deluxe, d3, d4);

        hotel.cancel(deluxe);
    }
}