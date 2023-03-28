package arsi.backend;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;

public final class GitHubURL implements Links {
    public String getData(String URL) throws URISyntaxException{
        String[] splitedURL = URL.split("/");
        String feedback = "Name: "+splitedURL[3]+" Repo: "+splitedURL[4];
        return feedback;
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
