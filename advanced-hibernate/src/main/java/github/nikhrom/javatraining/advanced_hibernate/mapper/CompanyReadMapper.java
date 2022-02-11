package github.nikhrom.javatraining.advanced_hibernate.mapper;

import github.nikhrom.javatraining.advanced_hibernate.dto.CompanyReadDto;
import github.nikhrom.javatraining.advanced_hibernate.entity.Company;
import org.hibernate.Hibernate;

public class CompanyReadMapper implements Mapper<Company, CompanyReadDto>{

    @Override
    public CompanyReadDto mapFrom(Company object) {
        Hibernate.initialize(object.getLocales());
        return CompanyReadDto.builder()
                .id(object.getId())
                .name(object.getName())
                .localeInfo(object.getLocales())
                .build();
    }
}
