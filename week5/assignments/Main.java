package week5.assignments;
class BookInventory {

    private int copiesTotal;
    private int copiesAvailable;

    BookInventory(int copiesTotal) {

        if (copiesTotal <= 0)
            throw new IllegalArgumentException();

        this.copiesTotal = copiesTotal;
        copiesAvailable = copiesTotal;
    }

    void checkOut() {

        if (copiesAvailable > 0)
            copiesAvailable--;
    }

    void checkIn() {

        if (copiesAvailable < copiesTotal)
            copiesAvailable++;
    }

    int getCopiesAvailable() {
        return copiesAvailable;
    }
}
class Main {
    public static void main(String[] args) {

        BookInventory b = new BookInventory(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();

        System.out.println(b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();

        System.out.println(b.getCopiesAvailable());
    }
}