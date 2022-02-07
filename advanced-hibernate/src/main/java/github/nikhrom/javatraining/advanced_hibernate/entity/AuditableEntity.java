package github.nikhrom.javatraining.advanced_hibernate.entity;

import github.nikhrom.javatraining.advanced_hibernate.listener.AuditListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
@EntityListeners(AuditListener.class)
public abstract class AuditableEntity<K extends Serializable> implements BaseEntity<K>{

    private Instant createdAt;
    private String createdBy;

    private Instant updatedAt;
    private String updatedBy;

}
