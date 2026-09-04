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

    ManagerEmployee(int empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(int empId, String empName, double salary, double stipendCap) {
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

public class F2 {
    public static void main(String[] args) {

        Employee normal = new Employee(101, "Arun", 40000);

        Employee manager = new ManagerEmployee(
                102, "Rahul", 70000, 8000);

        Employee intern = new InternEmployee(
                103, "Priya", 12000, 10000);

        if (normal instanceof ManagerEmployee) {
            System.out.println("Manager effective pay: "
                    + ((ManagerEmployee) normal).effectiveSalary());
        } else if (normal instanceof InternEmployee) {
            System.out.println("Intern effective pay: "
                    + ((InternEmployee) normal).effectiveSalary());
        } else {
            System.out.println("Plain employee pay: Rs "
                    + normal.getSalary());
        }

        if (manager instanceof ManagerEmployee) {
            System.out.println("Manager effective pay: Rs "
                    + ((ManagerEmployee) manager).effectiveSalary());
        }

        if (intern instanceof InternEmployee) {
            System.out.println("Intern effective pay: Rs "
                    + ((InternEmployee) intern).effectiveSalary());
        }
    }
}