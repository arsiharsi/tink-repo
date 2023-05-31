package configuration;

import dbController.DataBaseJDBCChatController;
import dbController.DataBaseJDBCLinkController;
import domain.jooq.tables.Chats;
import domain.jooq.tables.Links;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jooq")
public class JooqAccessConfiguration {
    @Bean
    Links linksService(Links linkController){
        return linkController;
    }
    @Bean
    Chats chatsService(Chats chatController){
        return chatController;
    }
}
