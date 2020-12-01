package springboot.jpatest.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springboot.jpatest.domain.Student;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private final EntityManager em;

    public void save(Student student){
        em.persist(student);
    }

    public Student findOne(Long id){
        return em.find(Student.class,id);
    }

    public List<Student> findAll(){
        return em.createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    public List<Student> findByStID(String stID){
        return em.createQuery("select s from Student s where s.stID = :stID",Student.class)
                .setParameter("stID",stID)
                .getResultList();
    }

    public List<Student> findByName(String name){
        return em.createQuery("select s from Student s where s.name = :name",Student.class)
                .setParameter("name",name)
                .getResultList();
    }

}//class
