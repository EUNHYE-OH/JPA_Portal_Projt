package springboot.jpatest.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollSearch {

    private String studentId; //학번
    private EnrollStatus enrollStatus; //수강 상태
}
