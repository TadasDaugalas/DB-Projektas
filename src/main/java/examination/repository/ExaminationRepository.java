package examination.repository;

import examination.entity.Exam;
import examination.entity.Question;
import examination.entity.User;
import org.hibernate.query.Query;

import java.util.List;


public class ExaminationRepository extends BaseRepository {
    public void startExam(Long userId, Long examId) {
        getEntity(session -> session.get(User.class, userId));
        getEntity(session -> session.get(Exam.class, examId));
    }
    public void printQuestion(Long examId) {
        List<Question> questions = questions(examId);
        questions.forEach(System.out::println);
    }

    public List<Question> questions(Long examId) {
        return getEntity(session -> {
            Query query = session.createQuery("From Question where examid=:examid", Question.class);
            query.setParameter("examid",examId);
            return query.list();
        });
    }
}