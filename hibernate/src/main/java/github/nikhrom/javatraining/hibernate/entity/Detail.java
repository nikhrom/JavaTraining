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
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "city")
    @Nullable
    private String city;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(mappedBy = "detail",
            cascade = CascadeType.ALL)
    private Employee employee;

    public Optional<String> getCity() {
        return Optional.ofNullable(city);
    }
}
