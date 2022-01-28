package github.nikhrom.javatraining.advanced_hibernate.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "username")
@ToString(exclude = "userChats")
@Table(name = "users", schema = "public")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public abstract class User {
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "users_id_seq", allocationSize = 1)*/
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats;
}
