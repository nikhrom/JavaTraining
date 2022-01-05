package github.nikhrom.javatraining.spring.mvc_hibernate.rest;

import github.nikhrom.javatraining.spring.mvc_hibernate.dto.CreateDepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.dto.DepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public DepartmentDto addDepartment(@RequestBody CreateDepartmentDto createDepartment){
        return departmentService.addDepartment(createDepartment);
    }

    @PutMapping
    public DepartmentDto updateDepartment(@RequestBody DepartmentDto updateDepartment){
        return departmentService.updateDepartment(updateDepartment);
    }

    @DeleteMapping("/{id:\\d+}")
    public DepartmentDto deleteDepartment(@PathVariable int id){
        return departmentService.deleteDepartmentById(id);
    }

}
