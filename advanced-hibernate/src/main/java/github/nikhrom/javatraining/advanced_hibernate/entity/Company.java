package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "name")
@ToString(exclude = {"users", "locales"})
@BatchSize(size = 3)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("username DESC, personalData.lastname DESC")
    //@JoinColumn(name = "company_id")
    private List<User> users = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "company_locale")
    @Builder.Default
    private List<LocaleInfo> locales = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
        user.setCompany(this);
    }
}
