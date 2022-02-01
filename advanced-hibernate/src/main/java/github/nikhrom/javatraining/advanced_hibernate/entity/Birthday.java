package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.jvnet.staxex.BinaryText;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Birthday implements Comparable<Birthday> {
    private LocalDate date;

    public long calculateAge(){
        return ChronoUnit.YEARS.between(date, LocalDate.now());
    }

    @Override
    public int compareTo(Birthday o) {
        return date.compareTo(o.date);
    }
}
