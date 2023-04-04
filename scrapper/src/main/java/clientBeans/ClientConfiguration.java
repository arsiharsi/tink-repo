package clientBeans;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class ClientConfiguration {

    @Bean("githubClient")
    public GHClient githubClient(WebClient githubWebClient) {
        return new GHClient();
    }

    @Bean("stackOverflowClient")
    public SOClient stackOverflowClient() {
        return new SOClient();
    }

}