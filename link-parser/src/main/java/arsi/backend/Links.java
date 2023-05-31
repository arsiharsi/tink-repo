package arsi.backend;

import reactor.core.publisher.Mono;

import java.net.URISyntaxException;

public sealed interface Links permits GitHubURL, StackOverflowURL {
    public default String getData(String URL) throws URISyntaxException {
        return "null";
    }
    default Mono<String> getAllLinks(String URL) throws URISyntaxException {
        return null;
    }
}
