package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.Value;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Value
public class Birthday {
    LocalDate date;

    public long getAge(){
        return ChronoUnit.YEARS.between(date, LocalDate.now());
    }
}
