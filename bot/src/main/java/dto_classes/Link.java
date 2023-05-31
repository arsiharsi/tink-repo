package dto_classes;


import java.io.Serializable;
import java.sql.Timestamp;


public record Link (long id, long chat_id, String link, Timestamp datetimestamp) {

}
