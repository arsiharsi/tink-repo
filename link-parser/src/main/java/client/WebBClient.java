package client;


import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.http.HttpClient;
import java.util.logging.Logger;



@Component
public class WebBClient {

    @Bean
    public static ClientResponse createWebClient(ClientRequest request)
    {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

        ClientResponse response = webClient.post().uri("/url:{url}")
                .body(Mono.just(request), ClientRequest.class)
                .retrieve()
                .bodyToMono(ClientResponse.class)
                .block();

        return response;
    }
}
