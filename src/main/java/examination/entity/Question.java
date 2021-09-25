package examination.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionText;
    private String answer1;
    private String answer2;
    private String answer3;
    private int correctAnswer;
    private int correctAnswerCount;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "examid")
    private Exam exam;

    public Question(String questionText, String answer1, String answer2, String answer3, int correctAnswer, int correctAnswerCount) {
        this.questionText = questionText;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.correctAnswer = correctAnswer;
        this.correctAnswerCount = correctAnswerCount;
    }

}
