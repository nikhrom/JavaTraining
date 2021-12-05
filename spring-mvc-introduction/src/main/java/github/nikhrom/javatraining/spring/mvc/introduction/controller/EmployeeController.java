package github.nikhrom.javatraining.spring.mvc.introduction.controller;


import github.nikhrom.javatraining.spring.mvc.introduction.dto.EmployeeDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping
    public String askEmployeeDetails(Model model){
        model.addAttribute("employee", new EmployeeDto());
        return "ask-employee-details";
    }

    @PostMapping
    public String viewEmployeeDetails(@ModelAttribute("employee") EmployeeDto employeeDto){
        return "view-employee-details";
    }

}
