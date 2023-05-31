package scrapperClients;

import dto_classes.Link;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class LinkClient {
    private static final String CHAT_BASE_URL = "http://localhost:8082/links";
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
    public void addLink(long chat_id,String url){
        try {
            webClient
                .post()
                .uri("/{chat_id}/{url}", chat_id, url).retrieve().toBodilessEntity().block();
        }
        catch (WebClientResponseException e){
            System.out.println("a");
        }
    }
    public void deleteLink(long chat_id,String url){
        try {
            webClient
                .delete()
                .uri("/{chat_id}/{url}", chat_id, url).retrieve().toBodilessEntity().block();
        }
        catch (WebClientResponseException e){
            System.out.println("a");
        }
    }
    public Link[] getLink(long chat_id){
        return webClient
                .get()
                .uri("/{chat_id}", chat_id)
            .retrieve().bodyToMono(Link[].class)
            .block();
    }

}
