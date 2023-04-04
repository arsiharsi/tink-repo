package configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tgBotClasses.TgBot;

@Configuration
public class TgBotConfiguration {
    @Bean("tg_bot_starter")
    public TgBot startBot(TgBot tgBot){
        return tgBot;
    }
}
