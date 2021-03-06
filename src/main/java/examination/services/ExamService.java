package examination.services;

import examination.entity.Exam;
import examination.repository.ExamRepository;

import java.util.List;

public class ExamService {
    private ExamRepository examRepository;
    public ExamService(){
        examRepository=new ExamRepository();
    }
    public void createExam(String name){
        if(name.trim().equals("")){
            System.out.println("Invalid exam name");
            return;
        }
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        examRepository.createExam(new Exam(null,name,date,0,0));
    }
    public List<Exam> getList(){
       return examRepository.getExams();
    }
    public Exam getExamById(Long id){
        return examRepository.getExam(id);
    }
}
