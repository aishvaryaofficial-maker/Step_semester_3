package week8.assignments;
import java.util.*;

interface CreditPolicy {
    int limit();
}

class Regular implements CreditPolicy {
    public int limit() {
        return 24;
    }
}

class Honors implements CreditPolicy {
    public int limit() {
        return 28;
    }
}

class Exchange implements CreditPolicy {
    public int limit() {
        return 20;
    }
}

class Student {
    String name;
    CreditPolicy policy;
    int credits;

    Student(String name, CreditPolicy policy, int credits) {
        this.name = name;
        this.policy = policy;
        this.credits = credits;
    }
}

class Elective {
    String name;
    int credit, capacity;

    ArrayList<Student> enrolled = new ArrayList<>();
    Queue<Student> waitlist = new LinkedList<>();

    Elective(String name, int credit, int capacity) {
        this.name = name;
        this.credit = credit;
        this.capacity = capacity;
    }

    boolean contains(Student s) {
        return enrolled.contains(s) || waitlist.contains(s);
    }

    void enroll(Student s) {

        if (contains(s)) {
            System.out.println(
                s.name + " is already enrolled/waitlisted.");
            return;
        }

        if (s.credits + credit > s.policy.limit()) {
            System.out.println(
                "Enrollment failed: " + s.name +
                " would exceed the credit limit.");
            return;
        }

        if (enrolled.size() < capacity) {
            enrolled.add(s);
            s.credits += credit;

            System.out.println(
                s.name + " enrolled in " + name +
                " (credits: " + s.credits +
                "/" + s.policy.limit() + ").");
        } else {
            waitlist.add(s);

            System.out.println(
                name + " is full. " + s.name +
                " added to waitlist.");
        }
    }

    void drop(Student s) {

        if (!enrolled.remove(s))
            return;

        s.credits -= credit;

        System.out.println(
            s.name + " dropped " + name +
            " (credits: " + s.credits +
            "/" + s.policy.limit() + ").");

        promote();
    }

    void promote() {

        Iterator<Student> it = waitlist.iterator();

        while (it.hasNext()) {
            Student s = it.next();

            if (s.credits + credit <= s.policy.limit()) {

                it.remove();
                enrolled.add(s);
                s.credits += credit;

                System.out.println(
                    s.name + " promoted from waitlist and enrolled in " +
                    name + " (credits: " + s.credits +
                    "/" + s.policy.limit() + ").");

                return;
            }
        }
    }
}

public class a4{
    public static void main(String[] args) {

        Elective cloud =
            new Elective("Cloud Computing", 4, 2);

        Student asha =
            new Student("Asha", new Regular(), 20);

        Student ravi =
            new Student("Ravi", new Honors(), 22);

        Student neha =
            new Student("Neha", new Exchange(), 12);

        Student kiran =
            new Student("Kiran", new Regular(), 22);

        cloud.enroll(asha);
        cloud.enroll(ravi);
        cloud.enroll(neha);
        cloud.enroll(kiran);

        cloud.drop(asha);
    }
}