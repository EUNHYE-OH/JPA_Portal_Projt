package springboot.jpatest.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class User{

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String type;
    private String name;
    private String password;
    private int birth;
    private String gender;
    private String address;
    private String uploadfile;

    public User() {
    }

    public User(Long id, String type, String name, String password, int birth, String gender, String address, String uploadfile) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
        this.address = address;
        this.uploadfile = uploadfile;
    }
}
