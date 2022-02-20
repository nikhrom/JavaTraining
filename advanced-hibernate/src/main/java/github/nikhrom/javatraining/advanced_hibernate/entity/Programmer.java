package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Programmer extends User{

    @Enumerated(EnumType.STRING)
    private Language language;

    @Builder(builderMethodName = "programmerBuilder")
    public Programmer(Long id, String username, PersonalData personalData, Role role, Company company, List<Payment> payments, List<UserChat> userChats, Language language) {
        super(id, username, personalData, role, company, payments, userChats);
        this.language = language;
    }
}
