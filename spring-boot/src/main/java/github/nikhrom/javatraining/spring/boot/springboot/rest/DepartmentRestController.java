package github.nikhrom.javatraining.spring.boot.springboot.rest;


import github.nikhrom.javatraining.spring.boot.springboot.dto.ReadDepartmentDto;
import github.nikhrom.javatraining.spring.boot.springboot.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentRestController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<ReadDepartmentDto> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

}
