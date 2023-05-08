package services;

import dto_classes.DataClass;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;
import tgBotClasses.TgBot;
import tgBotClasses.TgBotMethods;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@RabbitListener
public class ScrapperQueueListener {
    TgBotMethods botMethods;
    @RabbitHandler
    public void receiver(DataClass update) {
        for (int i = 0; i < update.getTgChatIds().length; i++){
            botMethods.sendMessage(update.getTgChatIds()[i], update.getDescription());
        }
    }
    public void setBotMethods(TgBotMethods tgBotMethods){
        this.botMethods = tgBotMethods;
    }
}
