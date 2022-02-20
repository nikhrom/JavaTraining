package github.nikhrom.javatraining.advanced_hibernate.listener;

import github.nikhrom.javatraining.advanced_hibernate.entity.Chat;
import github.nikhrom.javatraining.advanced_hibernate.entity.UserChat;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;

public class UserChatListener {

    @PostPersist
    public void postPersist(UserChat userChat){
        var chat = userChat.getChat();
        chat.setCount(chat.getCount() + 1);
    }

    @PostRemove
    public void postRemove(UserChat userChat){
        var chat = userChat.getChat();
        chat.setCount(chat.getCount() - 1);
    }
}
