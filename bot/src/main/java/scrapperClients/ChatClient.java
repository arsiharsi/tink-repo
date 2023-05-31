package scrapperClients;


import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class ChatClient {
    private static final String CHAT_BASE_URL = "http://localhost:8082/tg-chat";
    private WebClient webClient;
    private String URL;
    public ChatClient(){
        URL = CHAT_BASE_URL;
        webClient = WebClient.builder()
                .baseUrl(CHAT_BASE_URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public ChatClient(String customUrl){
        URL = customUrl;
        webClient = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public void addChat(long id){
        try {
            webClient
                .post()
                .uri("/" + id).retrieve().toBodilessEntity().block();
        }
        catch (WebClientResponseException e){
            System.out.println("a");
        }
    }
    public void deleteChat(long id){
        try {
            webClient
                .delete()
                .uri("/" + id).retrieve().toBodilessEntity().block();
        }
        catch (WebClientResponseException e){
            System.out.println("a");
        }
    }
}
