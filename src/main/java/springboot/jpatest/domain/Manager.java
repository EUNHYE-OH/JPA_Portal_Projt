package springboot.jpatest.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@DiscriminatorValue("M")
@Getter @Setter
public class Manager extends User{
    @Column(name = "mgID", unique = true)
    private String mgID;

    public Manager() {
        super();
    }

    public Manager(Long id, String type, String name, String password, int birth, String gender, String address, String uploadfile, String mgID) {
        super(id, type, name, password, birth, gender, address, uploadfile);
        this.mgID = mgID;
    }
}//class

