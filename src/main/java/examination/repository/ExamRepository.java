package examination.repository;

import examination.entity.Exam;

import java.util.List;

public class ExamRepository extends BaseRepository {
    public void createExam(Exam exam) {
        updateEntity(session -> session.save(exam));
    }

    public List<Exam> getExams() {
        return getEntity(session -> session.createQuery("From Exam", Exam.class).list());
    }

}
