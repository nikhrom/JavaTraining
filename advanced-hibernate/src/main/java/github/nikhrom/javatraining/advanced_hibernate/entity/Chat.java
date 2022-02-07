package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    private Integer count = 0;

    @OneToMany(mappedBy = "chat")
    @Builder.Default
    private List<UserChat> userChats = new ArrayList<>();

}
