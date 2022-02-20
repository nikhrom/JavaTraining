package github.nikhrom.javatraining.advanced_hibernate.mapper;

import github.nikhrom.javatraining.advanced_hibernate.dao.CompanyRepository;
import github.nikhrom.javatraining.advanced_hibernate.dto.UserCreateDto;
import github.nikhrom.javatraining.advanced_hibernate.entity.Company;
import github.nikhrom.javatraining.advanced_hibernate.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User>{

    private final CompanyRepository companyRepository;

    @Override
    public User mapFrom(UserCreateDto object) {
        return User.builder()
                .username(object.getUserName())
                .role(object.getRole())
                .personalData(object.getPersonalData())
                .company(companyRepository.findById(object.getCompanyId())
                        .orElseThrow(IllegalAccessError::new)
                )
                .build();
    }
}
