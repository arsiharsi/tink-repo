package dto_classes;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class DataClass {
    public DataClass(@NotNull int id,@NotNull String url,@NotNull String description,@NotNull int[] tgChatIds){
        this.id = id;
        this.url = url;
        this.description = description;
        this.tgChatIds = tgChatIds;
    }
    @NotNull
    private int id;
    @NotNull
    private String url;
    @NotNull
    private String description;
    @NotNull
    private int[] tgChatIds;

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
}
