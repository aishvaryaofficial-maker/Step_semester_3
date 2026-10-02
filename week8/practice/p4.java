package week8.practice;

abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract boolean canTakeLeave(int days);
}

class FullTime extends Employee {
    FullTime(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTime extends Employee {
    PartTime(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class LeaveRequest {
    Employee employee;
    String from, to;
    String status = "Pending";

    LeaveRequest(Employee e, String from, String to) {
        employee = e;
        this.from = from;
        this.to = to;
    }

    void approve() {
        if (status.equals("Pending")) {
            status = "Approved";
            System.out.println("Leave request for " +
                    employee.name + " approved.");
        }
    }

    void reject() {
        if (status.equals("Pending")) {
            status = "Rejected";
            System.out.println("Leave request rejected.");
        }
    }

    void makePending() {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change status: " +
                    status + " request cannot revert to Pending.");
        }
    }
}

public class p4 {
    public static void main(String[] args) {

        Employee john = new FullTime("John Doe");
        Employee jane = new PartTime("Jane Smith");

        LeaveRequest r1 =
            new LeaveRequest(john, "2024-10-10", "2024-10-12");

        System.out.println("Leave request submitted by " +
                john.name + ". Status: " + r1.status);

        r1.approve();
        System.out.println("Status: " + r1.status);

        LeaveRequest r2 =
            new LeaveRequest(jane, "2024-11-01", "2024-11-05");

        System.out.println("Leave request submitted by " +
                jane.name + ". Status: " + r2.status);

        r1.makePending();
    }
}