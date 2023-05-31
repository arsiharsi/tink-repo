package dto_classes;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class DataClass {
    public DataClass(@NotNull long id, @NotNull String url, @NotNull String description, @NotNull long tgChatIds){
        this.id = id;
        this.url = url;
        this.description = description;
        this.tgChatIds = tgChatIds;
    }
    @NotNull
    private long id;
    @NotNull
    private String url;
    @NotNull
    private String description;
    @NotNull
    private long tgChatIds;

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public long getTgChatIds() {
        return tgChatIds;
    }
}
