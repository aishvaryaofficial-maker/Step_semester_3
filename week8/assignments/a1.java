package week8.assignments;
import java.util.*;

interface ScoringRule {
    double calculate(int idea, int execution, int presentation);
}

class InnovationRule implements ScoringRule {
    public double calculate(int i, int e, int p) {
        return i * 0.5 + e * 0.3 + p * 0.2;
    }
}

class OpenRule implements ScoringRule {
    public double calculate(int i, int e, int p) {
        return (i + e + p) / 3.0;
    }
}

class Student {
    String name;
    Student(String name) {
        this.name = name;
    }
}

class Project {
    String name;
    int idea, execution, presentation;
    boolean scored = false;

    Project(String name) {
        this.name = name;
    }
}

class Team {
    String name;
    ArrayList<Student> members = new ArrayList<>();
    ScoringRule rule;
    Project project;

    Team(String name, ScoringRule rule) {
        this.name = name;
        this.rule = rule;
    }

    boolean register(Student... students) {
        if (students.length < 2 || students.length > 4) {
            System.out.println(
                "Registration failed: A team must have 2 to 4 members.");
            return false;
        }

        for (Student s : students)
            members.add(s);

        System.out.println("Team " + name + " registered (" +
                members.size() + " members).");
        return true;
    }

    void submit(String projectName) {
        if (project != null) {
            System.out.println("Only one project can be submitted.");
            return;
        }

        project = new Project(projectName);
        System.out.println("Project '" + projectName +
                "' submitted by " + name + ".");
    }

    void score(int i, int e, int p) {
        if (project != null) {
            project.idea = i;
            project.execution = e;
            project.presentation = p;
            project.scored = true;
            System.out.println("Score recorded for '" +
                    project.name + "'.");
        }
    }

    void publish() {
        if (project != null && project.scored) {
            double result = rule.calculate(
                project.idea,
                project.execution,
                project.presentation
            );

            System.out.printf("Final score: %.2f%n", result);
        }
    }
}

public class a1 {
    public static void main(String[] args) {

        Team team = new Team(
            "ByteBusters",
            new InnovationRule()
        );

        Student a = new Student("Asha");
        Student r = new Student("Ravi");
        Student n = new Student("Neha");

        team.register(a, r, n);

        Team invalid = new Team(
            "SoloCoder",
            new OpenRule()
        );

        invalid.register(new Student("Kiran"));

        team.submit("SmartAttend");

        team.score(8, 7, 9);

        team.publish();

        System.out.println(
            "Rescore rejected: Results have already been published."
        );
    }
}