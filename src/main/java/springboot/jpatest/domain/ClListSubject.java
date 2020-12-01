package springboot.jpatest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ClListSubject {

    @Id
    @GeneratedValue
    @Column(name="clList_subject_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subject_id")
    private Subject subject;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clList_id")
    private ClList clList;


    private int clListCredit; // 총 신청학점
    private int count; // 신청 과목수

    //==생성 메서드 ==//

    /**
     * 수강 신청 과목
     */
    public static ClListSubject createClListSubject(Subject subject, int clListCredit){
        ClListSubject clListSubject = new ClListSubject();
        clListSubject.setSubject(subject);
        clListSubject.setClListCredit(clListCredit);

        return clListSubject;
    }

/*    public static List<ClListSubject> createClListSubject(Subject subject){
        List<ClListSubject> clListSubjects = new ArrayList<ClListSubject>();
        clListSubjects.stream();
       // clListSubjects.setSubject(subject);

        return clListSubjects;
    }*/

    //==비즈니스 로직==//
    /**
     * 수강 철회
     */
   /* public void cancel(Subject subject, int clListCredit, int count){
        ClListSubject clListSubject = new ClListSubject();
        clListSubject.setSubject();
        clListSubject.setClListCredit(-=clListCredit);
    }*/

}
