class Employee {
    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(int empId, String empName,
            double salary, double teamBonus) {

        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(int empId, String empName,
            double salary, double stipendCap) {

        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        if (getSalary() < stipendCap) {
            return getSalary();
        }
        return stipendCap;
    }
}

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to " + slotNo);
        }
    }
}

class CompanyEmployeeRecord {

    String name;
    String empId;

    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
            Employee employee, ParkingSlot slot) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;

        totalRecords++;
    }

    String fullProfile() {

        double pay;

        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        String parking;

        if (slot == null) {
            parking = "no parking assigned";
        } else {
            parking = slot.slotNo;
        }

        return name + " | Pay: Rs " + pay + " | Slot: " + parking;
    }
}

public class F5 {

    public static void main(String[] args) {

        Employee manager = new ManagerEmployee(101, "Divya", 70000, 8000);

        Employee normal = new Employee(102, "Karan", 40000);

        Employee intern = new InternEmployee(103, "Meera", 12000, 10000);

        ParkingSlot slot1 = new ParkingSlot("A1", 1, 0);

        ParkingSlot slot2 = new ParkingSlot("A2", 1, 0);

        // Two employees get parking.
        slot1.allot("CAR101");
        slot2.allot("CAR102");

        CompanyEmployeeRecord record1 = new CompanyEmployeeRecord(
                "Divya", "101", manager, slot1);

        CompanyEmployeeRecord record2 = new CompanyEmployeeRecord(
                "Karan", "102", normal, slot2);

        // Meera intentionally has no parking.
        CompanyEmployeeRecord record3 = new CompanyEmployeeRecord(
                "Meera", "103", intern, null);

        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());

        System.out.println("Total records: "
                + CompanyEmployeeRecord.totalRecords);
    }
}