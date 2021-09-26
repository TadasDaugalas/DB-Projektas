package examination.repository;

import examination.entity.Exam;
import examination.entity.Examination;
import examination.entity.Question;
import examination.entity.User;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;


public class ExaminationRepository extends BaseRepository {
    public void createExamination(Examination examination) {
        updateEntity(session -> session.save(examination));
    }

    public List<Question> questions(Long examId) {
        return getEntity(session -> {
            Query query = session.createQuery("From Question where examid=:examid", Question.class);
            query.setParameter("examid", examId);
            return query.list();
        });
    }

    public Examination examinationId(Long id) {
        return getEntity(session -> session.get(Examination.class, id));
    }

    public void updateExamination(int totalquestion, int correctanswers, double grade, Long id) {
        updateEntity(session -> {
            Query query = session.createQuery("update Examination set totalQuestion=:totalQuestion , correctAnswers=:correctAnswers," +
                    "grade=:grade,endDate=:endDate where id=:id");
            query.setParameter("totalQuestion", totalquestion);
            query.setParameter("correctAnswers", correctanswers);
            query.setParameter("grade", grade);
            query.setParameter("endDate", new java.sql.Timestamp(new java.util.Date().getTime()));
            query.setParameter("id", id);
            query.executeUpdate();
        });
    }
    public List<Examination> examinationList() {
        return getEntity(session -> session.createQuery("From Examination", Examination.class).list());
    }
}