package entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Link implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private long chat_id;
    @Column(nullable = false)
    private String link_url;

    public long getId() {
        return id;
    }

    public long getChatId() {
        return chat_id;
    }

    public String getLink() {
        return link_url;
    }

}
