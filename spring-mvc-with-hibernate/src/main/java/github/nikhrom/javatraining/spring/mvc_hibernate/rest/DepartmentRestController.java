package github.nikhrom.javatraining.spring.mvc_hibernate.rest;

import github.nikhrom.javatraining.spring.mvc_hibernate.dto.CreateDepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.dto.DepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.entity.Department;
import github.nikhrom.javatraining.spring.mvc_hibernate.rest_exception.DepartmentIncorrectData;
import github.nikhrom.javatraining.spring.mvc_hibernate.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
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
                .orElseThrow(() -> new NoSuchElementException("Department with id = " + id +
                        " doesn't exist"));
    }

    @PostMapping
    public DepartmentDto addDepartment(@RequestBody CreateDepartmentDto createDepartment){
        return departmentService.addDepartment(createDepartment);
    }

}
