package github.nikhrom.javatraining.advanced_hibernate.entity;

import jdk.jfr.Enabled;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "chats")
    @Builder.Default
    private Set<User> users = new HashSet<>();

}
