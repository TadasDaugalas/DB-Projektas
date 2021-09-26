package examination.services;

import examination.entity.Exam;
import examination.entity.Examination;
import examination.entity.Question;
import examination.entity.User;
import examination.repository.ExaminationRepository;

import java.util.List;
import java.util.Scanner;

public class ExaminationService {
    Scanner sc = new Scanner(System.in);
    private final ExaminationRepository examinationRepository;
    public ExaminationService() {
        this.examinationRepository = new ExaminationRepository();
    }
//    public void exam(Long id){
//    examinationRepository.printQuestion(id);
//    }
    public void createExamination(User user, Exam exam){
        examinationRepository.createExamination(new Examination(exam,user));
    }
    public void questioning(Exam exam,Long id){
        List<Question> questionList = examinationRepository.questions(exam.getId());
        int totalQuestion =0;
        int correctAnswer =0;
        if(questionList.size()>0){
            for(Question question:questionList){
                totalQuestion++;
                int questionCorrectAnswCount = question.getCorrectAnswerCount();
                        System.out.println(question.getQuestionText());
                System.out.println("1 answer- " +question.getAnswer1() +" 2 answer -"+question.getAnswer2() + " 3 answer -"+question.getAnswer3());
                int studentAnswer = Integer.parseInt(sc.nextLine());
                if(studentAnswer==question.getCorrectAnswer()){
                    question.setCorrectAnswerCount(questionCorrectAnswCount+1);
                    exam.setCorrectAnswersCount(exam.getCorrectAnswersCount()+1);
                    correctAnswer++;
                }
            }
        }else {
            System.out.println("There is no questions in exam");
        }
        examinationRepository.updateExamination(totalQuestion,correctAnswer,(double) correctAnswer/totalQuestion*10,examinationRepository.examinationId(id).getId());
    }
    public List<Examination> getList(){
        return examinationRepository.examinationList();
    }


}
