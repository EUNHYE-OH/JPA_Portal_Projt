package springboot.jpatest.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClListSearch {

    private String stID; //학번
    private ClListStatus clListStatus; //수강 상태
}
