package springboot.jpatest.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectForm {

    private Long id;
    private String sbjID;
    private String sbjName;
    private int sbjCredit;
    private String classifi;
    private String professor;
}
