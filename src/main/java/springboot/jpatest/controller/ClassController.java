package springboot.jpatest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.jpatest.domain.*;
import springboot.jpatest.service.ClListService;
import springboot.jpatest.service.StudentService;
import springboot.jpatest.service.SubjectService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClassController {

    private final ClListService clListService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    /**
     * 신청가능한 과목리스트 -(이미 신청한 내역 빼기는 아직)
     */
    @GetMapping("/manager/clList")
    public String createForm(Model model, Subject subject){
        List<Student> students = studentService.findStudents();
        List<Subject> subjects = subjectService.findSubjects();

        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        return "clList/manager/clListForm";
    }

    @PostMapping("/manager/clList")
    public String clList(@RequestParam("studentId") Long studentId,
                         @RequestParam("subjectId") Long subjectId){
        clListService.clList(studentId,subjectId);
        return "redirect:/manager/clLists";
    }

/*    @GetMapping("/manager/clLists")
    public String managerClLists(@ModelAttribute("clListSearch")ClListSearch clListSearch, Model model){
        List<ClList> clLists = clListService.findClLists(clListSearch);
       *//* for(ClList clList : clLists){
            for(ClListSubject clListSubject : clList.getClListSubjects()){
                clListSubject.setSubject(clListSubject.getSubject());
            }
        }*//*
        model.addAttribute("clLists", clLists);
        return "clList/manager/clLists";
    }*/

    @GetMapping("/manager/clLists")
    public String managerClLists(Model model){
        List<ClList> clLists = clListService.findAll();
        model.addAttribute("clLists", clLists);
        return "clList/manager/clLists";
    }
}
