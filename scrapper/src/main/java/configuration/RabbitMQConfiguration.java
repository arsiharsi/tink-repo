package configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public record RabbitMQConfiguration() {
    @Bean
    public Binding bindingBean(Binding binding){
        return binding;
    }
    @Bean
    public Queue queueBean(Queue queue){
        return queue;
    }
    @Bean
    public DirectExchange directExchangeBean(DirectExchange directExchange){
        return directExchange;
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
