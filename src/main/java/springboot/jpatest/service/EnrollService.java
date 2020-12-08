package springboot.jpatest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpatest.domain.*;
import springboot.jpatest.repository.EnrollRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EnrollService {

    private final EnrollRepository enrollRepository;
    private final StudentService studentService;
    private final SubjectService subjectService;

    /**
     * 신청
     */
    @Transactional
    public Long enroll(Long studentId, Long subjectId){

        //엔티티 조회
        Student student = studentService.findOne(studentId);
        Subject subject = subjectService.findOne(subjectId);


        //수강 생성
        Enroll enroll = Enroll.createEnroll(student, subject);

        //수강 저장
        enrollRepository.save(enroll);
        return enroll.getId();
    }

    /**
     * 수강 철회
     */
    @Transactional
    public void cancelEnroll(Long enrollId){
        //수강 엔티티 조회
        Enroll enroll = enrollRepository.findOne(enrollId);

        //철회하는 로직
        enroll.cancel();

    }

    /**
     * 수강 검색
     */
    public List<Enroll> findEnrolls(EnrollSearch enrollSearch){
        return enrollRepository.findAllByCriteria(enrollSearch);
    }

    public List<Enroll> findAllWithSubject(){
        return enrollRepository.findAllWithSubject();
    }

    /*@Transactional
    public List<Enroll> searchPosts(String keyword){
        List<Enroll> enrolls = enrollInterfaceRepository.findByTitleContaining(keyword);
        List<Enroll> enrollList = new ArrayList<>();

        if(enrolls.isEmpty()) return enrollList;
        for(Enroll enroll : enrolls){
            enrollList.add(this.);
        }
        return enrollList;
    }

    private Enroll*/
}
