package springboot.jpatest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
           /* validateDuplicateSubject(subject);*/
            subjectRepository.save(subject);
    }

    /*private void validateDuplicateSubject(Subject subject){
        List<Subject> findSubjects = subjectRepository.findBySubjectCode(subject.getSubjectCode());
        if(!findSubjects.isEmpty()){
            throw new IllegalStateException("이미 존재하는 과목 코드입니다.");
        }
    }
*/
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
