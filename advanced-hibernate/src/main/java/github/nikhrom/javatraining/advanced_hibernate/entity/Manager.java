package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Manager extends User{

    private String projectName;

    @Builder(builderMethodName = "managerBuilder")
    public Manager(Long id, String username, PersonalData personalData, Role role, Company company, List<Payment> payments, List<UserChat> userChats, String projectName) {
        super(id, username, personalData, role, company, payments, userChats);
        this.projectName = projectName;
    }
}
