package springboot.jpatest.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springboot.jpatest.domain.Subject;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubjectRepository {

    private final EntityManager em;

    public void save(Subject subject){
        if(subject.getId() == null){
            em.persist(subject);
        }else{
            em.merge(subject);
        }
    }//save

    public Subject findOne(Long id){
        return em.find(Subject.class, id);
    }//findOne

    public List<Subject> findAll(){
        return em.createQuery("select sb from Subject sb", Subject.class)
                .getResultList();
    }

    public List<Subject> findBySbjID(String sbjID){
        return em.createQuery("select sb from Subject  sb where sb.sbjID=:sbjID",Subject.class)
                .setParameter("sbjID", sbjID)
                .getResultList();
    }

}
