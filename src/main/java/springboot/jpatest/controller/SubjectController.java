package springboot.jpatest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springboot.jpatest.domain.Subject;
import springboot.jpatest.service.SubjectService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/subjects/new")
    public String createForm(){
        return "subjects/createSubjectForm";
    }

    @PostMapping("/subjects/new")
    public String create(SubjectForm form){
        Subject subject = new Subject();
        subject.setId(form.getId());
        subject.setSbjID(form.getSbjID());
        subject.setSbjName(form.getSbjName());
        subject.setSbjCredit(form.getSbjCredit());
        subject.setClassifi(form.getClassifi());
        subject.setProfessor(form.getProfessor());

        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

    /**
     * 과목 목록
     */
    @GetMapping("/subjects")
    public String list(Model model, Subject subject){
        List<Subject> subjects = subjectService.findSubjects();
        model.addAttribute("subjects",subjects);
        return "subjects/subjectList";
    }

    /**
     * 과목 수정
     */
    @GetMapping("/subjects/{subjectId}/edit")
    public String updateSubjectForm(@PathVariable("subjectId") Long subjectId, Model model){
        Subject subject = subjectService.findOne(subjectId);

        SubjectForm form = new SubjectForm();
        form.setId(subject.getId());
        form.setSbjID(subject.getSbjID());
        form.setSbjName(subject.getSbjName());
        form.setSbjCredit(subject.getSbjCredit());
        form.setClassifi(subject.getClassifi());
        form.setProfessor(subject.getProfessor());

        model.addAttribute("form",form);
        return "subjects/updateSubjectForm";
    }

    @PostMapping("/subjects/{subjectId}/edit")
    public String updateSubject(@ModelAttribute("form") SubjectForm form){
        Subject subject = new Subject();
        subject.setId(form.getId());
        subject.setSbjID(form.getSbjID());
        subject.setSbjCredit(form.getSbjCredit());
        subject.setClassifi(form.getClassifi());
        subject.setProfessor(form.getProfessor());

        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }
}
