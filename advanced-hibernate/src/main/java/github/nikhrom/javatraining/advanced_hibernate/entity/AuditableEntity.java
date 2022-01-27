package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity<K extends Serializable> implements BaseEntity<K>{

    private Instant createdAt;

    private String createdBy;

}
