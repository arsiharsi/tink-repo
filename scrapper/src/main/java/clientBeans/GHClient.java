package clientBeans;

import dto_classes.GHResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
//import ru.tinkoff.edu.java.scrapper.dto.webClient.GithubResponse;

public class GHClient {
    private static final String GITHUB_BASE_URL = "https://api.github.com";
    private WebClient webClient;
    private String URL;

    public GHClient(String customURL){
        URL = customURL;
        webClient = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public GHClient(){
        URL = GITHUB_BASE_URL;
        webClient = WebClient.builder()
                .baseUrl(GITHUB_BASE_URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public GHResponse fetchRepository(String user, String repo){
        return webClient
                .get()
                .uri("/repos/{user}/{repo}", user, repo)
                .retrieve()
                .bodyToMono(GHResponse.class)
                .block();
    }
}
