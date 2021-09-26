package examination.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "examinations")
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "examid")
    private Exam exam;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private User user;
    private int totalQuestion;
    private int correctAnswers;
    private double grade;
    private Timestamp startDate;
    private Timestamp endDate;

    public Examination(Exam exam, User user) {
        this.exam = exam;
        this.user = user;
        this.startDate = new java.sql.Timestamp(new java.util.Date().getTime());
    }
}
