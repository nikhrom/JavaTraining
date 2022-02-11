package github.nikhrom.javatraining.advanced_hibernate.dto;

import github.nikhrom.javatraining.advanced_hibernate.entity.PersonalData;
import github.nikhrom.javatraining.advanced_hibernate.entity.Role;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class UserCreateDto {
    @NotNull
    PersonalData personalData;
    String userName;
    String info;
    Role role;
    Integer companyId;
}
