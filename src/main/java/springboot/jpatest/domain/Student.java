package springboot.jpatest.domain;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("S")
@Getter @Setter
public class Student extends User{

    @Column(name = "stID", unique = true)
    private String stID;
    private String major;

    //@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "student")
    private List<ClList> clLists = new ArrayList<>();

    public Student() {
        super();
    }

    public Student(Long id, String type, String name, String password, int birth, String gender, String address, String uploadfile, String stID, String major, List<ClList> clLists) {
        super(id, type, name, password, birth, gender, address, uploadfile);
        this.stID = stID;
        this.major = major;
        this.clLists = clLists;
    }
}
