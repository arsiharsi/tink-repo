package clientBeans;


import dto_classes.SOResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.zip.GZIPInputStream;

public class SOClient {
    private static final String STACK_OVERFLOW_BASE_URL = "https://api.stackexchange.com/2.3/";
    private WebClient webClient;
    private String URL;

    public SOClient(String customURL){
        URL = customURL;
        this.webClient = WebClient.create(URL);
    }

    public SOClient(){
        URL = STACK_OVERFLOW_BASE_URL;
        this.webClient = WebClient.create(URL);
    }

    public SOResponse fetchQuestion(long id){
        /*
        webClient.get().uri("/questions/{id}?order=desc&sort=activity&site=stackoverflow", id)
            .accept(MediaType.APPLICATION_JSON)
            .header("accept-encoding", "gzip")
            .exchange()
            .flatMap(encodedResponse -> encodedResponse.body((inputMessage, context) ->
                inputMessage.getBody().flatMap(dataBuffer -> {
                    ClientResponse.Builder decodedResponse = ClientResponse.from(encodedResponse);
                    try {
                        GZIPInputStream gz = new GZIPInputStream(dataBuffer.asInputStream());
                        decodedResponse.body(new String(gz.readAllBytes()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    decodedResponse.headers(headers -> {
                        headers.remove("content-encoding");
                    });
                    return Mono.just(decodedResponse.build());
                }).flatMap(clientResponse -> clientResponse.bodyToMono(SOResponse.class));

         */
        return webClient
                .get()
                .uri("/questions/{id}?order=desc&sort=activity&site=stackoverflow", id)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_ENCODING, "gzip")
                .retrieve()
                .bodyToMono(SOResponse.class)
                .block();
    }
}
