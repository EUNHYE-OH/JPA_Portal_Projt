package springboot.jpatest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpatest.domain.Student;
import springboot.jpatest.domain.User;
import springboot.jpatest.repository.StudentRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    /**
     * 회원가입
     */

    public void save(Student student){
        studentRepository.save(student);
    }

    /**
     * 수정
     */

    /**
     * 전체 회원 조회
     */
    public List<Student> findStudents(){
        return studentRepository.findAll();
    }

    public Student findOne(Long studentId){
        return studentRepository.findOne(studentId);
    }
}
