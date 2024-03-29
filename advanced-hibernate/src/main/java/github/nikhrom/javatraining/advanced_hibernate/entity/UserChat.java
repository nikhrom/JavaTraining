package github.nikhrom.javatraining.advanced_hibernate.entity;

import github.nikhrom.javatraining.advanced_hibernate.listener.UserChatListener;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.Instant;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users_chat")
@EntityListeners(UserChatListener.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserChat extends AuditableEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "created_by")
    private String createdBy;

    public UserChat(Long id, User user, Chat chat, Instant createdAt, String createdBy) {
        this.id = id;
        setUser(user);
        setChat(chat);
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public void setUser(User user){
        this.user = user;
        user.getUserChats().add(this);
    }

    public void setChat(Chat chat){
        this.chat = chat;
        chat.getUserChats().add(this);
    }
}
