package springboot.jpatest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpatest.domain.Subject;
import springboot.jpatest.repository.SubjectRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Transactional
    public void saveSubject(Subject subject){
        subjectRepository.save(subject);
    }

    public List<Subject> findSubjects(){
        return subjectRepository.findAll();
    }

    public Subject findOne(Long subjectId){
        return subjectRepository.findOne(subjectId);
    }

    public List<Subject> findBySubjectCode(String subjectCode){
        return subjectRepository.findBySubjectCode(subjectCode);
    }
}
