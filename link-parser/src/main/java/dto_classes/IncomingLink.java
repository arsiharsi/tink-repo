package dto_classes;

import jakarta.validation.constraints.NotNull;

public record IncomingLink(@NotNull String url) {
}
