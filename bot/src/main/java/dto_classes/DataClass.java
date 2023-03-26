package dto_classes;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DataClass {
    public DataClass(int in_id, String in_url, String in_description, int[] in_tgChatIds){
        id = in_id;
        url = in_url;
        description = in_description;
        tgChatIds = in_tgChatIds;
    }
    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public int[] getTgChatIds() {
        return tgChatIds;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTgChatIds(int[] tgChatIds) {
        this.tgChatIds = tgChatIds;
    }

    //@NotNull
    private int id;
    //@NotNull
    private String url;
   // @NotNull
    private String description;
   // @NotNull
    private int[] tgChatIds;
}
