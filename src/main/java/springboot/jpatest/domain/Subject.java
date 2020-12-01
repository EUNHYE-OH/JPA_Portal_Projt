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

    private String sbjID;
    private String sbjName;
    private int sbjCredit;
    private String classifi;
    private String professor;

    /*@OneToMany(mappedBy = "subject")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clList_id")
    private ClList clLists;*/

    //==비즈니스 로직==//
/*    public void addSubject(Subject subject){

    }*/

}
