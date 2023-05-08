package configuration;

import dto_classes.DataClass;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public record RabbitMQConfiguration() {
    public static final String QUEUE_MESSAGES = "messages-queue";
    public static final String EXCHANGE_MESSAGES = "messages-exchange";
    public static final String QUEUE_MESSAGES_DLQ = "messages-dlq";
    @Bean
    Queue messagesQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", QUEUE_MESSAGES_DLQ)
                .build();
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES_DLQ).build();
    }
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
    public ClassMapper classMapper(){
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("dto_classes.DataClass", DataClass.class);

        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("dto_classes.*");
        classMapper.setIdClassMapping(mappings);
        return classMapper;
    }
    @Bean
    public MessageConverter jsonMessageConverter(ClassMapper classMapper) {
        Jackson2JsonMessageConverter jsonConverter=new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper);
        return jsonConverter;
    }
}
