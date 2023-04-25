package configuration;

import dao.JpaChatDao;
import dao.JpaLinkDao;
import dbController.DataBaseJDBCChatController;
import dbController.DataBaseJDBCLinkController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    @Bean
    JpaLinkDao linksService(JpaLinkDao linkController){
        return linkController;
    }
    @Bean
    JpaChatDao chatsService(JpaChatDao chatController){
        return chatController;
    }
}
