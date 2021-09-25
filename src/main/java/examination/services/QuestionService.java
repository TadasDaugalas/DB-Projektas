package examination.services;

import examination.entity.Exam;
import examination.entity.Question;
import examination.repository.ExamRepository;
import examination.repository.QuestionRepository;
import org.hibernate.query.Query;

import java.util.Scanner;

public class QuestionService {
    private boolean checkCorrectAnsw=true;
    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;
    Scanner sc = new Scanner(System.in);
    public QuestionService() {
        this.questionRepository = new QuestionRepository();
        this.examRepository = new ExamRepository();
    }
    public boolean createQuestion(Long examId,String text,String answ1,String answ2,String answ3,int correctAnsw){
        if(questionValidation(text,answ1,answ2,answ3,correctAnsw)) {
            Exam exam = examRepository.getExam(examId);
            Question question = new Question(text, answ1, answ2, answ3, correctAnsw, 0);

            exam.getQuestions().add(question);
            question.setExam(exam);

            questionRepository.createQuestion(question);
        }
        return true;
    }
    public boolean updateQuestion(Long questionId,String text,String answ1,String answ2,String answ3,int correctAnsw){

        if(questionValidation(text,answ1,answ2,answ3,correctAnsw)){
        questionRepository.updateQuestion(questionId,text,answ1,answ2,answ3,correctAnsw);
        }
        return true;
    }
    private boolean questionValidation(String text,String answ1,String answ2,String answ3,int correctAnsw){
        if(text.trim().equals("")){
            System.out.println("Question text is empty");
            return false;
        }
        if(answ1.trim().equals("")){
            System.out.println("Answer 1 is empty");
            return false;
        }
        if(answ2.trim().equals("")){
            System.out.println("Answer 2 is empty");
            return false;
        }
        if(answ3.trim().equals("")){
            System.out.println("Answer 3 is empty");
            return false;
        }
        while (checkCorrectAnsw){
            if(correctAnsw ==1||correctAnsw ==2||correctAnsw ==3){
                System.out.println("Answer setted");
                checkCorrectAnsw=false;
            }else {
                System.out.println("Answer should be 1 or 2 or 3");
                System.out.println("Insert again");
                correctAnsw=Integer.parseInt(sc.nextLine());
            }
        }
        return true;
    }
}
