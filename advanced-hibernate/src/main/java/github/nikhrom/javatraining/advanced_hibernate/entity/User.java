package github.nikhrom.javatraining.advanced_hibernate.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.FetchProfile.FetchOverride;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@NamedEntityGraph(name = "withCompanyAndChats",
        attributeNodes = {
            @NamedAttributeNode("company"),
            @NamedAttributeNode(value = "userChats", subgraph = "chats")
        },
        subgraphs = {
            @NamedSubgraph(name = "chats", attributeNodes = @NamedAttributeNode("chat"))
        }
)
@FetchProfile(name = "withCompanyAndPayments", fetchOverrides = {
        @FetchOverride(entity = User.class, association = "company", mode = FetchMode.JOIN),
        @FetchOverride(entity = User.class, association = "payments", mode = FetchMode.JOIN)
})
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
@Audited
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Users")
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

    @NotAudited
    @Builder.Default
    @OneToMany(mappedBy = "receiver")
    private List<Payment> payments = new ArrayList<>();

    @NotAudited
    @Builder.Default
    @OneToMany(mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<UserChat> userChats = new ArrayList<>();

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.username);
    }
}
