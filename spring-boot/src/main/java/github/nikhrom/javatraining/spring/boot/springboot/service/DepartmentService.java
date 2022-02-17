package github.nikhrom.javatraining.spring.boot.springboot.service;

import github.nikhrom.javatraining.spring.boot.springboot.dto.ReadDepartmentDto;
import github.nikhrom.javatraining.spring.boot.springboot.mapper.ReadDepartmentDtoMapper;
import github.nikhrom.javatraining.spring.boot.springboot.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ReadDepartmentDtoMapper readDepartmentDtoMapper;

    public List<ReadDepartmentDto> getAllDepartments() {
        return departmentRepository.getAll()
                .stream()
                .map(readDepartmentDtoMapper::mapFrom)
                .collect(toList());
    }

}
