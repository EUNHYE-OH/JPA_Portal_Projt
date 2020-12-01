package springboot.jpatest.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectForm {

    private Long id;
    private String subjectCode;
    private String subjectName;
    private int subjectCredit;
    private String classification;
    private String professor;
}
