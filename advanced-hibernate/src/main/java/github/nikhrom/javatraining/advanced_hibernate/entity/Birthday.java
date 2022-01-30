package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.Value;
import org.jvnet.staxex.BinaryText;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Value
public class Birthday implements Comparable<Birthday> {
    LocalDate date;

    public long getAge(){
        return ChronoUnit.YEARS.between(date, LocalDate.now());
    }


    @Override
    public int compareTo(Birthday o) {
        return date.compareTo(o.date);
    }
}
