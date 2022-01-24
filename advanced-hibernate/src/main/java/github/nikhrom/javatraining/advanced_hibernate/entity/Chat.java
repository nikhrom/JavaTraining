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
@EqualsAndHashCode(exclude = "userChats")
@ToString(exclude = "userChats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "chat")
    @Builder.Default
    private Set<UserChat> userChats = new HashSet<>();

}
