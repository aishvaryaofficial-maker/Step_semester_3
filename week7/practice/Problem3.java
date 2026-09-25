package week7.practice;

interface Auditable {
    String auditRecord();
}

abstract class StaffMember {
    private double baseSalary;
    protected double bonusRate;

    StaffMember(double baseSalary) {
        this(baseSalary, 0.10);
    }

    StaffMember(double baseSalary, double bonusRate) {
        this.baseSalary = baseSalary;
        this.bonusRate = bonusRate;
    }

    public abstract double calculateBonus();

    public double getSalary() {
        return baseSalary;
    }

    public void setSalary(double baseSalary) {
        if (baseSalary >= 0) {
            this.baseSalary = baseSalary;
        }
    }
}

class TeamLead extends StaffMember implements Auditable {
    private int teamSize;

    TeamLead(double baseSalary, int teamSize) {
        this(baseSalary, 0.10, teamSize);
    }

    TeamLead(double baseSalary, double bonusRate, int teamSize) {
        super(baseSalary, bonusRate);
        this.teamSize = teamSize;
    }

    public double calculateBonus() {
        return getSalary() * bonusRate;
    }

    public String auditRecord() {
        return "TeamLead audit: " + teamSize
                + " team members, salary $" + getSalary();
    }
}

public class Problem3 {

    static String getAuditIfApplicable(StaffMember s) {
        if (s instanceof Auditable) {
            Auditable a = (Auditable) s;
            return a.auditRecord();
        }

        return "No audit record";
    }

    public static void main(String[] args) {

        TeamLead t1 =
            new TeamLead(60000, 5);

        TeamLead t2 =
            new TeamLead(60000, 0.20, 5);

        System.out.println(t1.calculateBonus());
        System.out.println(t2.calculateBonus());

        t1.setSalary(-5000);

        System.out.println(t1.getSalary());

        StaffMember ref = t1;

        System.out.println(getAuditIfApplicable(ref));
    }
}