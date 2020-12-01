package springboot.jpatest.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import springboot.jpatest.domain.ClList;
import springboot.jpatest.domain.ClListSearch;
import springboot.jpatest.domain.Student;
import springboot.jpatest.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClListRepository {

    private final EntityManager em;

    public void save(ClList clList){
        em.persist(clList);
    }

    public ClList findOne(Long id){
        return em.find(ClList.class,id);
    }

    public List<ClList> findAllByString(ClListSearch clListSearch){
        //JPQL
        String jpql = "select c From ClList c join c.student s";
        boolean isFirstCondition = true;

        //수강 상태 검색
        if(clListSearch.getClListStatus() != null){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            }else{
                jpql += " and";
            }
            jpql += " c.status = :status";
        }

        //학번 검색
        if(StringUtils.hasText(clListSearch.getStID())){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            }else{
                jpql += " add";
            }
            jpql += " s.stID like :stID";
        }

        TypedQuery<ClList> query = em.createQuery(jpql, ClList.class)
                .setMaxResults(1000); //최대 1000건
        if (clListSearch.getClListStatus() != null){
            query = query.setParameter("status", clListSearch.getClListStatus());
        }
        if(StringUtils.hasText(clListSearch.getStID())){
            query = query.setParameter("stID", clListSearch.getStID());
        }
        return query.getResultList();
    }

    public List<ClList> findAllByCriteria(ClListSearch clListSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ClList> cq = cb.createQuery(ClList.class);
        Root<ClList> c = cq.from(ClList.class);
        Join<ClList, Student> s = c.join("student", JoinType.INNER); //학생과 조인
        List<Predicate> criteria = new ArrayList<>();
        //수강 신청 상태 검색
        if (clListSearch.getClListStatus() != null) {
            Predicate status = cb.equal(c.get("status"),
                    clListSearch.getClListStatus());
            criteria.add(status);
        }
        //학번 검색
        if (StringUtils.hasText(clListSearch.getStID())) {
            Predicate stID =
                    cb.like(s.<String>get("stID"), "%" +
                            clListSearch.getStID() + "%");
            criteria.add(stID);
        }
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<ClList> query = em.createQuery(cq).setMaxResults(1000); //최대1000건
        return query.getResultList();
    }

    public List<ClList> findAllWithSubject(){
        return em.createQuery(
                "select distinct c from ClList c" +
                        " join fetch c.student s " +
                        " join fetch c.clListSubjects cs" +
                        " join fetch cs.subject sb", ClList.class)
                .getResultList();
    }

    public List<ClList> findAll(){
        return em.createQuery("select c from ClList c", ClList.class)
                .getResultList();
    }


}//class
