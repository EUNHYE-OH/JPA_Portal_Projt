package springboot.jpatest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = { "/","home"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String home(){
        return "home";
    }

    @GetMapping("/address")
    public String address(){
        return "view/address";
    }

    @GetMapping("/view/studentLayout")
    public String viewStudentLayout(){
        return "view/studentLayout";
    }
    @GetMapping("/view/managerLayout")
    public String viewManagerLayout(){
        return "view/managerLayout";
    }

}
