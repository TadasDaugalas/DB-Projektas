package examination.services;

import examination.entity.Exam;
import examination.entity.Examination;
import examination.entity.Question;
import examination.entity.User;
import examination.repository.ExamRepository;
import examination.repository.ExaminationRepository;
import examination.repository.QuestionRepository;

import java.util.List;
import java.util.Scanner;

public class ExaminationService {
    Scanner sc = new Scanner(System.in);
    private final ExaminationRepository examinationRepository;
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;

    public ExaminationService() {
        this.examinationRepository = new ExaminationRepository();
        this.examRepository = new ExamRepository();
        this.questionRepository = new QuestionRepository();
    }

    public void createExamination(User user, Exam exam) {
        examinationRepository.createExamination(new Examination(exam, user));
    }

    public void questioning(Exam exam, Long id) {
        List<Question> questionList = examinationRepository.questions(exam.getId());
        int totalQuestion = 0;
        int correctAnswer = 0;
        int correctAnswerToExam = exam.getCorrectAnswersCount();
        if (questionList.size() > 0) {
            for (Question question : questionList) {
                totalQuestion++;
                System.out.println(question.getQuestionText());
                System.out.println("1 answer- " + question.getAnswer1() + " 2 answer -" + question.getAnswer2() + " 3 answer -" + question.getAnswer3());
                int studentAnswer = Integer.parseInt(sc.nextLine());
                if (studentAnswer == question.getCorrectAnswer()) {
                    correctAnswerToExam++;
                    correctAnswer++;
                    questionRepository.updateQuestionCount(question.getCorrectAnswerCount() + 1, question.getId());
                }
            }
        } else {
            System.out.println("There is no questions in exam");
        }
        examinationRepository.updateExamination(totalQuestion, correctAnswer, (double) correctAnswer / totalQuestion * 10, examinationRepository.examinationId(id).getId());
        examRepository.updateExam(exam.getTries() + 1,correctAnswerToExam, exam.getId());
    }

    public List<Examination> getList() {
        return examinationRepository.examinationList();
    }


}
