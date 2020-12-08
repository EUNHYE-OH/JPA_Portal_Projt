package springboot.jpatest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.jpatest.domain.*;
import springboot.jpatest.service.EnrollService;
import springboot.jpatest.service.StudentService;
import springboot.jpatest.service.SubjectService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EnrollController {

    private final EnrollService enrollService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    /**
     * 신청가능한 과목리스트 -(이미 신청한 내역 빼기는 아직)
     */
    @GetMapping("/manager/enroll")
    public String createForm(Model model, Subject subject){
        List<Student> students = studentService.findStudents();
        List<Subject> subjects = subjectService.findSubjects();

        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        return "enroll/manager/enrollForm";
    }

    @PostMapping("/manager/enroll")
    public String enroll(@RequestParam("studentId") Long studentId,
                         @RequestParam("subjects") Long subjects){
        enrollService.enroll(studentId,subjects);
        return "redirect:/manager/enrolls";
    }

    /*검색 > 리스트*/
    @GetMapping("/manager/enrolls")
    public String managerEnrolls(@ModelAttribute("enrollSearch")EnrollSearch enrollSearch, Model model){
        List<Enroll> enrolls = enrollService.findEnrolls(enrollSearch);
        model.addAttribute("enrolls", enrolls);
        return "enroll/manager/enrollList";
    }
/*    @GetMapping("/manager/enrolls")
    public String managerEnrolls(Model model){
        List<Enroll> enrolls = enrollService.findAllWithSubject();
        model.addAttribute("enrolls", enrolls);
        return "enroll/manager/enrolls";
    }*/

    /**
     * 취소
     */
    @PostMapping("/enrolls/{enrollId}/cancel")
    public String cancelEnroll(@PathVariable("enrollId") Long enrollId){
        enrollService.cancelEnroll(enrollId);
        return "redirect:/manager/enrolls";
    }
}
