package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.file.OpenOption;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Serializable entityId;

    private String entityName;

    @Column(length = 1000)
    private String entityContent;

    @Enumerated(EnumType.STRING)
    private Operation operation;

    public enum Operation{
        SAVE, UPDATE, DELETE, INSERT
    }
}
