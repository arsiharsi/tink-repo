package entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "chats")
public class Chat implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private long chat_id;
    public long getId() {
        return id;
    }

    public long getChatId() {
        return chat_id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setChat_id(long chatId) {
        this.chat_id = chatId;
    }
}
