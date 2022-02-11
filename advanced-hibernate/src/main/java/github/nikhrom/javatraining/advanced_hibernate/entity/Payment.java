package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.time.Instant;

@Data
@EqualsAndHashCode(of = {"id", "amount"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Payment extends AuditableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "receiver_id")
    private User receiver;
}
