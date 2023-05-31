package dto_classes;

import reactor.core.publisher.Mono;

public record LinkData(String data, Mono<String> links) {
}
