package examination.repository;

import examination.entity.Exam;
import org.hibernate.query.Query;


import java.util.List;

public class ExamRepository extends BaseRepository {
    public void createExam(Exam exam) {
        updateEntity(session -> session.save(exam));
    }

    public List<Exam> getExams() {
        return getEntity(session -> session.createQuery("From Exam", Exam.class).list());
    }
    public Exam getExam(Long id){
return getEntity(session -> session.get(Exam.class,id));
    }
    public void updateExam(int tries,int correctAnswersCount,Long id){
        updateEntity(session -> {
            Query query = session.createQuery("update Exam set tries=:tries,correctAnswersCount=:correctAnswersCount where id=:id");
            query.setParameter("tries",tries);
            query.setParameter("correctAnswersCount",correctAnswersCount);
            query.setParameter("id",id);
            query.executeUpdate();

        });
    }

}
