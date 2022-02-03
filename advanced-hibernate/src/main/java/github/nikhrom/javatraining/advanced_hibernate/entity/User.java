package github.nikhrom.javatraining.advanced_hibernate.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "findByUserName", query =
        "select u from User u where u.username = :username"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"userChats", "payments"})
@Table(name = "users", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
@Builder
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class User implements Comparable<User>, BaseEntity<Long>{
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "users_id_seq", allocationSize = 1)*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private PersonalData personalData;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @Type(type = "jsonb")
//    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id")
    private Company company;

    @Builder.Default
    @OneToMany(mappedBy = "receiver")
    private List<Payment> payments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.username);
    }
}
