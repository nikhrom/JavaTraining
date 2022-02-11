package github.nikhrom.javatraining.advanced_hibernate.mapper;

import github.nikhrom.javatraining.advanced_hibernate.dto.UserReadDto;
import github.nikhrom.javatraining.advanced_hibernate.entity.User;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto mapFrom(User object) {
        return UserReadDto.builder()
                .id(object.getId())
                .username(object.getUsername())
                .personalData(object.getPersonalData())
                .role(object.getRole())
                .company(Optional.ofNullable(object.getCompany())
                        .map(companyReadMapper::mapFrom)
                        .orElse(null)
                )
                .build();
    }
}
