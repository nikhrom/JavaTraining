package github.nikhrom.javatraining.spring.mvc_hibernate.controller;


import github.nikhrom.javatraining.spring.mvc_hibernate.dto.CreateDepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.dto.DepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;


    @GetMapping
    String showDepartments(Model model) {
        var allDepartments = departmentService.getAllDepartments();
        model.addAttribute("departments", allDepartments);

        return "show-departments";
    }

    @GetMapping("/add")
    String addDepartment(Model model) {
        model.addAttribute("department", new CreateDepartmentDto());
        return "add-department";
    }

    @PostMapping("/add")
    String onAddDepartment(@ModelAttribute("department") CreateDepartmentDto departmentDto) {
        departmentService.addDepartment(departmentDto);
        return "redirect:/department";
    }

    @GetMapping("/update/{id:\\d+}")
    String updateDepartment(@PathVariable String id, Model model) {
        var department = departmentService.getDepartmentById(Integer.valueOf(id));
        department.ifPresent(departmentDto -> model.addAttribute("department", departmentDto));
        return "update-department";
    }

    @PostMapping("/update/{id:\\d+}")
    String onUpdateDepartment(@ModelAttribute("department") DepartmentDto departmentDto) {
        departmentService.updateDepartment(departmentDto);
        return "redirect:/department";
    }

    @RequestMapping("/delete/{id:\\d+}")
    String deleteDepartment(@PathVariable String id, Model model) {
        departmentService.deleteDepartmentById(Integer.valueOf(id));
        return "redirect:/department";
    }
}