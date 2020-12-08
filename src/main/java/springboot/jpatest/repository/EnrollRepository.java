package springboot.jpatest.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import springboot.jpatest.domain.Enroll;
import springboot.jpatest.domain.EnrollSearch;
import springboot.jpatest.domain.Student;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EnrollRepository {

    private final EntityManager em;

    public void save(Enroll enroll){
        em.persist(enroll);
    }

    public Enroll findOne(Long id){
        return em.find(Enroll.class,id);
    }
/*
    public List<Enroll> findAllByString(EnrollSearch enrollSearch){
        //JPQL
        String jpql = "select e From Enroll e join e.student s";
        boolean isFirstCondition = true;

        //수강 상태 검색
        if(enrollSearch.getEnrollStatus() != null){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            }else{
                jpql += " and";
            }
            jpql += " e.status = :status";
        }

        //학번 검색
        if(StringUtils.hasText(enrollSearch.getStudentId())){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            }else{
                jpql += " add";
            }
            jpql += " s.studentId like :studentId";
        }

        TypedQuery<Enroll> query = em.createQuery(jpql, Enroll.class)
                .setMaxResults(1000); //최대 1000건
        if (enrollSearch.getEnrollStatus() != null){
            query = query.setParameter("status", enrollSearch.getEnrollStatus());
        }
        if(StringUtils.hasText(enrollSearch.getStudentId())){
            query = query.setParameter("studentId", enrollSearch.getStudentId());
        }
        return query.getResultList();
    }*/

    public List<Enroll> findAllByCriteria(EnrollSearch enrollSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Enroll> cq = cb.createQuery(Enroll.class);
        Root<Enroll> e = cq.from(Enroll.class);
        Join<Enroll, Student> s = e.join("student", JoinType.INNER); //학생과 조인
        List<Predicate> criteria = new ArrayList<>();
        //수강 신청 상태 검색
        if (enrollSearch.getEnrollStatus() != null) {
            Predicate status = cb.equal(e.get("status"),
                    enrollSearch.getEnrollStatus());
            criteria.add(status);
        }
        //학번 검색
        if (StringUtils.hasText(enrollSearch.getStudentId())) {
            Predicate studentId =
                    cb.like(s.<String>get("studentId"), "%" +
                            enrollSearch.getStudentId() + "%");
            criteria.add(studentId);
        }
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Enroll> query = em.createQuery(cq).setMaxResults(1000); //최대1000건
        return query.getResultList();
    }

    public List<Enroll> findAllWithSubject(){
        return em.createQuery(
                "select distinct e from Enroll e" +
                        " join fetch e.student s " +
                        " join fetch e.subject sb", Enroll.class)
                .getResultList();
    }



}//class
