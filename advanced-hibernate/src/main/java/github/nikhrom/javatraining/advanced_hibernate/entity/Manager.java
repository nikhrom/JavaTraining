package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manager extends User{

    private String projectName;

    @Builder
    public Manager(Long id, String username, PersonalData personalData, Role role, Company company, List<UserChat> userChats, String projectName) {
        super(id, username, personalData, role, company, userChats);
        this.projectName = projectName;
    }
}
