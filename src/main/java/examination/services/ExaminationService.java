package examination.services;

import examination.repository.ExaminationRepository;

public class ExaminationService {
    private final ExaminationRepository examinationRepository;
    public ExaminationService() {
        this.examinationRepository = new ExaminationRepository();
    }
    public void exam(Long id){
    examinationRepository.printQuestion(id);
    }

}
