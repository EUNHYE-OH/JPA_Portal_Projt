package springboot.jpatest.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@DiscriminatorValue("M")
@Getter @Setter
public class Manager extends User{
    @Column(name = "manager_id", unique = true)
    private String managerId;

    public Manager() {
        super();
    }

    public Manager(Long id, String type, String name, String password, int birth, String gender, String address, String uploadfile, String managerId) {
        super(id, type, name, password, birth, gender, address, uploadfile);
        this.managerId = managerId;
    }
}//class

