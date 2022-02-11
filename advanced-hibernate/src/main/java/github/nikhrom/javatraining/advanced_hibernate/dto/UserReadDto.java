package github.nikhrom.javatraining.advanced_hibernate.dto;

import github.nikhrom.javatraining.advanced_hibernate.entity.PersonalData;
import github.nikhrom.javatraining.advanced_hibernate.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserReadDto{
    Long id;
    String username;
    PersonalData personalData;
    Role role;
    CompanyReadDto company;
}
