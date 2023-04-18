package scrapperClients;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class LinkClient {
    private static final String CHAT_BASE_URL = "https://localhost:8080/links";
    private WebClient webClient;
    private String URL;
    public LinkClient(){
        URL = CHAT_BASE_URL;
        webClient = WebClient.builder()
                .baseUrl(CHAT_BASE_URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public LinkClient(String customUrl){
        URL = customUrl;
        webClient = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public void addLink(long id){
        webClient
                .post()
                .header("id", String.valueOf(id));
    }
    public void deleteLink(long id){
        webClient
                .delete()
                .header("id", String.valueOf(id));
    }
    public void getLink(long id){
        webClient
                .get()
                .header("id", String.valueOf(id));
    }

}
