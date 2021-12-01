package github.nikhrom.javatraining.spring.mvc.introduction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class FirstController{


    @GetMapping("/")
    private String showFirstView(){
        return "first-view";
    }

}
