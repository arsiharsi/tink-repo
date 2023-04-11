package entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Chat implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private long chatId;
    public long getId() {
        return id;
    }

    public long getChatId() {
        return chatId;
    }

}
