class FeeAccount {
    private double totalFee;
    private double amountPaid;

    FeeAccount(double totalFee) {
        this.totalFee = totalFee;
        amountPaid = 0;
    }

    void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        } else {
            System.out.println("Payment rejected.");
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(double totalFee) {
        super(totalFee);
    }

    void payInTwoInstallments(double amount) {
        pay(amount);
        pay(amount);
    }
}

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot(String name) {
        if (occupied < beds) {
            occupied++;
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (HostelRoom room : rooms) {
            if (room != null && room.occupied < room.beds) {
                return room;
            }
        }

        return null;
    }

    static HostelRoom allotRoom(HostelRoom[] rooms) {
        HostelRoom room = findAvailableRoom(rooms);

        if (room != null) {
            room.occupied++;
        }

        return room;
    }
}

class SrmStudent {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    SrmStudent(String name, String regNo, HostelFeeAccount feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        totalStudents++;
    }

    String fullStatus() {
        String roomName;

        if (room == null) {
            roomName = "unallotted";
        } else {
            roomName = room.roomNo;
        }

        return name + " | Due: Rs " + feeAccount.getDue()
                + " | Room: " + roomName;
    }
}

public class Main {
    public static void main(String[] args) {

        HostelRoom[] rooms = {
                new HostelRoom("C-214", 1, 0),
                new HostelRoom("C-507", 1, 0)
        };

        SrmStudent ravi = new SrmStudent("Ravi", "101",
                new HostelFeeAccount(150000));

        SrmStudent anitha = new SrmStudent("Anitha", "102",
                new HostelFeeAccount(200000));

        SrmStudent karthik = new SrmStudent("Karthik", "103",
                new HostelFeeAccount(200000));

        // Valid payment
        ravi.feeAccount.pay(10000);

        // Another valid payment
        anitha.feeAccount.pay(20000);

        // Invalid payment
        karthik.feeAccount.pay(-5000);

        // Allot rooms only to two students
        ravi.room = HostelRoom.allotRoom(rooms);
        anitha.room = HostelRoom.allotRoom(rooms);

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println("Total students: "
                + SrmStudent.totalStudents);
    }
}