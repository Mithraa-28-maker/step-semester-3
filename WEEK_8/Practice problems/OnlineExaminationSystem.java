import java.util.*;

abstract class Question {
    String text;
    int marks;

    Question(String text, int marks) {
        this.text = text;
        this.marks = marks;
    }

    abstract boolean checkAnswer(String answer);
}

class MCQ extends Question {
    String correct;

    MCQ(String text, String correct, int marks) {
        super(text, marks);
        this.correct = correct;
    }

    boolean checkAnswer(String answer) {
        return answer.equals(correct);
    }
}

class TrueFalse extends Question {
    String correct;

    TrueFalse(String text, String correct, int marks) {
        super(text, marks);
        this.correct = correct;
    }

    boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase(correct);
    }
}

class Attempt {
    Student student;
    List<Question> questions = new ArrayList<>();
    List<String> answers = new ArrayList<>();
    boolean submitted = false;

    Attempt(Student student, List<Question> questions) {
        this.student = student;
        this.questions = questions;
    }

    void answer(String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }

        answers.add(answer);
        System.out.println("Answer recorded.");
    }

    void submit() {
        submitted = true;

        int total = 0;
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            total += questions.get(i).marks;

            if (questions.get(i).checkAnswer(answers.get(i))) {
                score += questions.get(i).marks;
                System.out.println("Question " + (i + 1) +
                        ": Correct (" + questions.get(i).marks + " points)");
            } else {
                System.out.println("Question " + (i + 1) +
                        ": Incorrect (0 points)");
            }
        }

        System.out.println("Total score: " + score + "/" + total);
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {

        Student s = new Student("Student 1");

        List<Question> questions = new ArrayList<>();

        questions.add(new MCQ("Q1", "C", 5));
        questions.add(new TrueFalse("Q2", "False", 5));

        Attempt attempt = new Attempt(s, questions);

        System.out.println("Exam A started by Student 1.");

        attempt.answer("C");
        attempt.answer("True");

        System.out.println("Exam A submitted by Student 1.");
        attempt.submit();

        attempt.answer("B");
    }
}