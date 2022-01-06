package github.nikhrom.javatraining.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hello {

    @GetMapping("/first")
    public String first(){
        return "first";
    }

}
