package springboot.jpatest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Subject {

    @Id @GeneratedValue
    @Column(name = "subject_id")
    private Long id;

    private String subjectCode;
    private String subjectName;
    private int subjectCredit;
    private String classification;
    private String professor;
    private String[] subjects;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Enroll> enroll = new ArrayList<>();

    //==비즈니스 로직==//
/*    public void addSubject(Subject subject){

    }*/

}
