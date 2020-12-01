package springboot.jpatest.domain;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("S")
@Getter @Setter
public class Student extends User{

    @Column(name = "student_id" ,unique = true)
    private String studentId;
    private String major;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Enroll> enrolls = new ArrayList<>();

    public Student() {
        super();
    }

    public Student(Long id, String type, String name, String password, int birth, String gender, String address, String uploadfile, String studentId, String major, List<Enroll> enrolls) {
        super(id, type, name, password, birth, gender, address, uploadfile);
        this.studentId = studentId;
        this.major = major;
        this.enrolls = enrolls;
    }
}
