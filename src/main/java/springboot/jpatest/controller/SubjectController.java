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
    public String create(SubjectForm form, String subjectCode, Model model){
        if(subjectService.findBySubjectCode(subjectCode).isEmpty()) {
            Subject subject = new Subject();
            subject.setId(form.getId());
            subject.setSubjectCode(form.getSubjectCode());
            subject.setSubjectName(form.getSubjectName());
            subject.setSubjectCredit(form.getSubjectCredit());
            subject.setClassification(form.getClassification());
            subject.setProfessor(form.getProfessor());
            subjectService.saveSubject(subject);
            return "redirect:/subjects";
        }else{
            model.addAttribute("msg","이미 존재하는 과목코드입니다.");
            return "subjects/createSubjectForm";
        }
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
        form.setSubjectCode(subject.getSubjectCode());
        form.setSubjectName(subject.getSubjectName());
        form.setSubjectCredit(subject.getSubjectCredit());
        form.setClassification(subject.getClassification());
        form.setProfessor(subject.getProfessor());

        model.addAttribute("form",form);
        return "subjects/updateSubjectForm";
    }

    @PostMapping("/subjects/{subjectId}/edit")
    public String updateSubject(@ModelAttribute("form") SubjectForm form){
        Subject subject = new Subject();
        subject.setId(form.getId());
        subject.setSubjectCode(form.getSubjectCode());
        subject.setSubjectName(form.getSubjectName());
        subject.setSubjectCredit(form.getSubjectCredit());
        subject.setClassification(form.getClassification());
        subject.setProfessor(form.getProfessor());

        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }
}
