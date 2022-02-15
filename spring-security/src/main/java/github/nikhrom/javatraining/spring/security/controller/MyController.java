package github.nikhrom.javatraining.spring.security.controller;

import github.nikhrom.javatraining.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getInfoForAllEmployees(){
        System.out.println(userService.getAll());
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
