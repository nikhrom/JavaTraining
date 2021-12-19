package github.nikhrom.javatraining.spring.mvc_hibernate.controller;


import github.nikhrom.javatraining.spring.mvc_hibernate.dto.DepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;


    @GetMapping
    String showDepartments(Model model){
        var allDepartments = departmentService.getAllDepartments();
        model.addAttribute("departments", allDepartments);

        return "show-departments";
    }

    @GetMapping("/add")
    String addDepartment(Model model){
        model.addAttribute("department", new DepartmentDto());
        return "add-department";
    }

    @PostMapping("/add")
    String onAddDepartment(@ModelAttribute("department") DepartmentDto departmentDto){
        departmentService.addDepartment(departmentDto);
        return "redirect:/department";
    }
}
