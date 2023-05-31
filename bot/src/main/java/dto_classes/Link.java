package dto_classes;


import java.io.Serializable;
import java.sql.Timestamp;


public class Link implements Serializable {

    private long id;

    private long chat_id;

    private String link;

    private Timestamp datetimestamp;
    public void setId(long id) {
        this.id = id;
    }

    public void setDatetimestamp(Timestamp datetimestamp) {
        this.datetimestamp = datetimestamp;
    }

    public void setChat_id(long chat_id) {
        this.chat_id = chat_id;
    }

    public void setLink_url(String link_url) {
        this.link = link_url;
    }


    public Timestamp getDatetimestamp() {
        return datetimestamp;
    }

    public long getId() {
        return this.id;
    }

    public long getChatId() {
        return this.chat_id;
    }

    public String link() {
        return this.link;
    }

}
