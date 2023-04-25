package configuration;

import dbController.DataBaseJDBCChatController;
import dbController.DataBaseJDBCLinkController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcAccessConfiguration {
    @Bean
    DataBaseJDBCLinkController linksService(DataBaseJDBCLinkController linkController){
        return linkController;
    }
    @Bean
    DataBaseJDBCChatController chatsService(DataBaseJDBCChatController chatController){
        return chatController;
    }
}
