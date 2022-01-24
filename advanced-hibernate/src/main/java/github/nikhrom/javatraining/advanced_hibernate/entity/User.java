package github.nikhrom.javatraining.advanced_hibernate.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(of = "username")
@Table(name = "users", schema = "public")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class User {
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

    @Type(type = "jsonb")
    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    @Builder.Default
    @JoinTable(name = "users_chat",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private Set<Chat> chats = new HashSet<>();

    public void addChat(Chat chat){
        chats.add(chat);
        chat.getUsers().add(this);
    }
}
