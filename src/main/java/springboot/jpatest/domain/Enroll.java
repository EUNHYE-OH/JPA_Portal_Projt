package springboot.jpatest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Enroll {

    @Id @GeneratedValue
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private int applyYear;
    private String applySemester;


    @Enumerated(EnumType.STRING)
    private EnrollStatus status; //수강 상태 [Enrolment, Cancel]

    //==연관관계 메소드==//

    public void setStudent(Student student){
        this.student = student;
        student.getEnrolls().add(this);
    }


    //==생성 메서드==//
    public static Enroll createEnroll(Student student, Subject subject){
        Enroll enroll = new Enroll();
        enroll.setStudent(student);
        enroll.setSubject(subject);
        enroll.setStatus(EnrollStatus.Enrolment);
        return enroll;
    }

    //==비즈니스 로직==//
    /**
     * 수강 철회
     */
  public void cancel(){
        if(EnrollStatus.Completed == getStatus()){
            throw new IllegalStateException("이미 수강 완료된 강의입니다.");
        }
        this.setStatus(EnrollStatus.CANCEL);
    }

    /**
     * 수강 신청 6개월 후 수강완료로 변경
     */
    /*public void changeStatus(){
        if(applyDate.getDayOfMonth() + 6 == LocalDateTime.now().getDayOfMonth()){
            this.setStatus(EnrollStatus.Completed);
        }
    }*/

    //==조회 로직==//

    /**
     * 전체 수강 학점 조회
     */
    /*public int getTotalCredit(){
        int totalCredit = 0;
        for(ClListSubject clListSubject : clListSubjects){
            totalCredit += clListSubject.getClListCredit();
        }
        return totalCredit;
    }*/

}
