package examination.repository;
import examination.entity.Question;
import org.hibernate.query.Query;

import java.util.List;


public class QuestionRepository extends BaseRepository {
    public void createQuestion(Question question) {
        updateEntity(session -> session.save(question));
    }

    public List<Question> getQuestionsList(Long examid) {
        List<Question> questions=getEntity(session -> {
          Query query=  session.createQuery("From Question where examid=:examid", Question.class);
          query.setParameter("examid",examid);
          return query.list();
        });
        return questions;
    }
    public void updateQuestion(Long id,String questiontext,String answer1,String answer2,String answer3,int correctAnswer){
    updateEntity(session -> {
        Query query =session.createQuery("update Question set questiontext=:questiontext,answer1=:answer1," +
                "answer2=:answer2,answer3=:answer3,correctAnswer=:correctAnswer where id=:id");
        query.setParameter("questiontext",questiontext);
        query.setParameter("answer1",answer1);
        query.setParameter("answer2",answer2);
        query.setParameter("answer3",answer3);
        query.setParameter("correctAnswer",correctAnswer);
        query.setParameter("id",id);
        query.executeUpdate();


    });
    }
    public void updateQuestionCount(int correctAnswerCount,Long id){
        updateEntity(session -> {
            Query query = session.createQuery("update Question set correctAnswerCount=:correctAnswerCount where id=:id");
            query.setParameter("correctAnswerCount",correctAnswerCount);
            query.setParameter("id",id);
            query.executeUpdate();

        });
    }
}
