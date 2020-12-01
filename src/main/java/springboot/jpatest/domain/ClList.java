package springboot.jpatest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
public class ClList {
    @Id @GeneratedValue
    @Column(name = "clList_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    /*@ManyToOne(fetch = FetchType.LAZY)*/
    @OneToMany(mappedBy = "clList", cascade = CascadeType.ALL)
    private List<ClListSubject> clListSubjects = new ArrayList<>();

    private int appYear;
    private String appSemester;

    private LocalDateTime applyDate;

    @Enumerated(EnumType.STRING)
    private ClListStatus status; //수강 상태 [Enrolment, Cancel]

    //==연관관계 메소드==//

    public void setStudent(Student student){
        this.student = student;
        student.getClLists().add(this);
    }

    public void addClListSubject(ClListSubject clListSubject){
        clListSubjects.add(clListSubject);
        clListSubject.setClList(this);
    }

    //==생성 메서드==//
    public static ClList createClList(Student student, ClListSubject... clListSubjects){
        ClList clList = new ClList();
        clList.setStudent(student);
        for(ClListSubject clListSubject : clListSubjects){
            clList.addClListSubject(clListSubject);
        }
        clList.setStatus(ClListStatus.Enrolment);
        clList.setApplyDate(LocalDateTime.now());
        return clList;
    }

    //==비즈니스 로직==//
    /**
     * 수강 철회
     */
 /*   public void cancel(){
        if(ClListStatus.Completed == getStatus()){
            throw new IllegalStateException("이미 수강 완료된 강의입니다.");
        }
        this.setStatus(ClListStatus.CANCEL);
        for(ClListSubject clListSubject : clListSubjects){
            clListSubject.cancel();
        }
    }*/

    /**
     * 수강 신청 6개월 후 수강완료로 변경
     */
    public void changeStatus(){
        if(applyDate.getDayOfMonth() + 6 == LocalDateTime.now().getDayOfMonth()){
            this.setStatus(ClListStatus.Completed);
        }
    }

    //==조회 로직==//

    /**
     * 전체 수강 학점 조회
     */
    public int getTotalCredit(){
        int totalCredit = 0;
        for(ClListSubject clListSubject : clListSubjects){
            totalCredit += clListSubject.getClListCredit();
        }
        return totalCredit;
    }

}
