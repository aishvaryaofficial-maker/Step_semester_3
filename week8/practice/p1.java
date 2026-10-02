package week8.practice;

import java.util.*;

abstract class Question {
    String text, correctAnswer;

    Question(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    abstract boolean checkAnswer(String answer);
}

class MCQ extends Question {
    MCQ(String text, String correctAnswer) {
        super(text, correctAnswer);
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Attempt {
    Student student;
    ArrayList<Question> questions = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();
    boolean submitted = false;

    Attempt(Student s, ArrayList<Question> q) {
        student = s;
        questions = q;
    }

    void answer(int n, String ans) {
        if (!submitted) {
            while (answers.size() < questions.size())
                answers.add("");
            answers.set(n, ans);
            System.out.println("Question " + (n + 1) +
                    " answered with '" + ans + "'.");
        }
    }

    void submit() {
        submitted = true;
        int score = 0;

        for (int i = 0; i < questions.size(); i++)
            if (questions.get(i).checkAnswer(answers.get(i)))
                score++;

        System.out.println("Examination submitted successfully.");
        System.out.println("Result: " + score + "/" + questions.size() + " correct");
    }
}

public class p1 {
    public static void main(String[] args) {
        Student s = new Student("John");

        ArrayList<Question> q = new ArrayList<>();
        q.add(new MCQ("2+2=?", "A"));
        q.add(new MCQ("Capital of India?", "B"));

        Attempt a = new Attempt(s, q);

        System.out.println("Examination 'Math Quiz' started by " + s.name + ".");
        a.answer(0, "A");
        a.answer(1, "C");
        a.submit();
    }
}