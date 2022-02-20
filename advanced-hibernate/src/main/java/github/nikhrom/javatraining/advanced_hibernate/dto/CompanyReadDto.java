package github.nikhrom.javatraining.advanced_hibernate.dto;

import github.nikhrom.javatraining.advanced_hibernate.entity.Company;
import github.nikhrom.javatraining.advanced_hibernate.entity.LocaleInfo;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.function.Function;

@Value
@Builder
public class CompanyReadDto{
    Integer id;
    String name;
    List<LocaleInfo> localeInfo;
}
