package springboot.jpatest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import springboot.jpatest.domain.Student;
import springboot.jpatest.domain.User;
import springboot.jpatest.service.StudentService;

import java.util.List;

@Slf4j //로그남기기
@Controller
@RequiredArgsConstructor
public class UserController {

    private StudentService studentService;


    @GetMapping("/user/new")
    public String joinForm(){
        return "user/joinPreForm";
    }

/*    @PostMapping("/joinPre")
    public ModelAndView joinPre(ModelAndView mv,User user, Student student){
        mv.addObject("type", user.getType());
        mv.setViewName("user/joinForm");
        return mv;
    }*/


    @GetMapping("user/findIdPop")
    public String findIdPop(){
        return "user/findIdPop";
    }

    @GetMapping("/user/login")
    public String loginForm(){
        return "home";
    }

    /* null error */
    @GetMapping("/students")
    public String studentList(Model model){
        List<Student> students = studentService.findStudents();
        model.addAttribute("students", students);
        return "user/studentList";
    }



}
