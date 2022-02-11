package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


public interface BaseEntity<K extends Serializable>{

    void setId(K id);
    K getId();
}
