package github.nikhrom.javatraining.advanced_hibernate.listener;

import github.nikhrom.javatraining.advanced_hibernate.entity.AuditableEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.Instant;

public class AuditDateListener {

    @PrePersist
    public void prePersist(AuditableEntity<?> entity){
        entity.setCreatedAt(Instant.now());
    }

    @PreUpdate
    public void preUpdate(AuditableEntity<?> entity){
        entity.setUpdatedAt(Instant.now());
    }
}
