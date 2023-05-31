package arsi.backend;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;

public final class StackOverflowURL implements Links{
    public String getData(String URL){
        return URL.split("/")[4];
    }
    public Mono<String> getAllLinks(String URL) throws URISyntaxException {
        WebClient client = WebClient.create();

        WebClient.ResponseSpec responseSpec = client.get()
                .uri(URL)
                .retrieve();
        Mono<String> responseBody = responseSpec.bodyToMono(String.class);
        return responseBody;
    }
}
