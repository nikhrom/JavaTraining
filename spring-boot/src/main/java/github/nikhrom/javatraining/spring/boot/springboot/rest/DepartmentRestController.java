package github.nikhrom.javatraining.spring.boot.springboot.rest;


import github.nikhrom.javatraining.spring.boot.springboot.dto.CreateDepartmentDto;
import github.nikhrom.javatraining.spring.boot.springboot.dto.ReadDepartmentDto;
import github.nikhrom.javatraining.spring.boot.springboot.dto.UpdateDepartmentDto;
import github.nikhrom.javatraining.spring.boot.springboot.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentRestController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<ReadDepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id:\\d+}")
    public ReadDepartmentDto getDepartment(@PathVariable int id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public ReadDepartmentDto addDepartment(@RequestBody CreateDepartmentDto createDepartment) {
        return departmentService.addDepartment(createDepartment);
    }

    @PutMapping
    public ReadDepartmentDto updateDepartment(@RequestBody UpdateDepartmentDto updateDepartment) {
        return departmentService.updateDepartment(updateDepartment);
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartmentById(id);
        return ResponseEntity.ok("Department with id = " + id + " was deleted");
    }

}
