package services;

import dto_classes.DataClass;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;
import tgBotClasses.TgBot;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@RabbitListener
public class ScrapperQueueListener {
    @RabbitHandler
    public void receiver(DataClass update, TgBot tgBot) {
        // TODO
    }
}
