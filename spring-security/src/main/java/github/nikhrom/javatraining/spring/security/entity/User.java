package github.nikhrom.javatraining.spring.security.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "authorities")
@ToString(exclude = "authorities")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private Boolean enabled;

    @OneToMany(mappedBy = "user")
    private List<Authority> authorities;
}
