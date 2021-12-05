package github.nikhrom.javatraining.spring.mvc.introduction.controller;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller("firstController")
public class FirstController{

    @GetMapping("/")
    public String showFirstView(Model model){
        model.addAttribute("lang", "en");
        return "first-view";
    }

}
