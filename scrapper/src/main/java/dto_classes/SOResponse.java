package dto_classes;

import java.time.OffsetDateTime;
import java.util.Date;

public record SOResponse(String title, Date last_activity_date) {
}
