package github.nikhrom.javatraining.hibernate.entity;


import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;


@Entity
@Table(name = "detail")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Detail{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Builder.Default
    private Integer id = 0;

    @Column(name = "city")
    @Nullable
    private String city;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    public Optional<String> getCity() {
        return Optional.ofNullable(city);
    }
}
