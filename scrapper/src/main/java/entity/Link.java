package entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "links")
public class Link implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private long chat_id;
    @Column(nullable = false)
    private String link_url;
    public void setId(long id) {
        this.id = id;
    }

    public void setChat_id(long chat_id) {
        this.chat_id = chat_id;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }



    public long getId() {
        return this.id;
    }

    public long getChatId() {
        return this.chat_id;
    }

    public String getLink() {
        return this.link_url;
    }

}
