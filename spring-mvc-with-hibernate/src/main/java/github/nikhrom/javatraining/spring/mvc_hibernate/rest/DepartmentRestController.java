package github.nikhrom.javatraining.spring.mvc_hibernate.rest;

import github.nikhrom.javatraining.spring.mvc_hibernate.dto.DepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.entity.Department;
import github.nikhrom.javatraining.spring.mvc_hibernate.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentRestController {

    private final DepartmentService departmentService;


    @GetMapping
    public List<DepartmentDto> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id:\\d+}")
    public DepartmentDto getDepartment(@PathVariable int id){
        return departmentService.getDepartmentById(id)
                .orElse(new DepartmentDto());
    }
}
