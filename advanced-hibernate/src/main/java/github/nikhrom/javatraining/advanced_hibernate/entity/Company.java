package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.io.Serializable;
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
@Audited
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Companies")
public class Company implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @NotAudited
    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("username DESC, personalData.lastname DESC")
    //@JoinColumn(name = "company_id")
    private List<User> users = new ArrayList<>();

    @NotAudited
    @ElementCollection
    @CollectionTable(name = "company_locale")
    @Builder.Default
    private List<LocaleInfo> locales = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
        user.setCompany(this);
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
