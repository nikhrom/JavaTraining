package github.nikhrom.javatraining.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/")
    public String getInfoForAllEmployees(){
        return "view-for-all-employees";
    }

    @GetMapping("/hr")
    public String getInfoForHr(){
        return "view-for-hr";
    }

    @GetMapping("/it")
    public String getInfoForIt(){
        return "view-for-it";
    }


}
