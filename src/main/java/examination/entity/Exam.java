package examination.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Timestamp date;
    private int tries;
    private int correctAnswersCount;
    @OneToMany(mappedBy = "exam",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Question> questions;

    public Exam(Long id, String name, Timestamp date, int tries, int correctAnswersCount) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.tries = tries;
        this.correctAnswersCount = correctAnswersCount;
    }
}
