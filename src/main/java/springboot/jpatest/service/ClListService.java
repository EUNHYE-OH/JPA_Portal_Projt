package springboot.jpatest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpatest.domain.*;
import springboot.jpatest.repository.ClListRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClListService {

    private final ClListRepository clListRepository;
    private final StudentService studentService;
    private final SubjectService subjectService;

    /**
     * 신청
     */
    /*@Transactional
    public Long clList(Long studentId, Long subjectId){

        //엔티티 조회
        Student student = studentService.findOne(studentId);
        Subject subject = subjectService.findOne(subjectId);

        //수강 과목 생성
        ClListSubject clListSubject = ClListSubject.createClListSubject(subject, subject.getSbjCredit());

        //수강 생성
        ClList clList = ClList.createClList(student,clListSubject);

        //수강 저장
        clListRepository.save(clList);
        return clList.getId();
    }*/

    @Transactional
    public Long clList(Long studentId, Long subjectId){

        //엔티티 조회
        Student student = studentService.findOne(studentId);
        Subject subject = subjectService.findOne(subjectId);
        //List<Subject> subjects = subjectService.findBySbjID(sbjID);

        //수강 과목 생성
        ClListSubject clListSubject = ClListSubject.createClListSubject(subject, subject.getSbjCredit());

        //수강 생성
        ClList clList = ClList.createClList(student,clListSubject);

        //수강 저장
        clListRepository.save(clList);
        return clList.getId();
    }

    /**
     * 수강 철회
     */
    @Transactional
    public void cancelClList(Long clListId){
        //수강 엔티티 조회
        ClList clList = clListRepository.findOne(clListId);

        //철회하는 로직
    }

    /**
     * 수강 검색
     */
    public List<ClList> findClLists(ClListSearch clListSearch){
        return clListRepository.findAllByCriteria(clListSearch);
    }

    public List<ClList> findAll(){
        return clListRepository.findAll();
    }
}
