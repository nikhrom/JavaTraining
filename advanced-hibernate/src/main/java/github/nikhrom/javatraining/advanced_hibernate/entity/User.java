package github.nikhrom.javatraining.advanced_hibernate.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import github.nikhrom.javatraining.advanced_hibernate.converter.BirthdayConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    private String username;
    private String firstname;
    private String lastname;


    @Column(name = "birthday")
    private Birthday birthday;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Type(type = "jsonb")
    private String info;
}
