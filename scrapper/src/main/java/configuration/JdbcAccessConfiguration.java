package configuration;

import dbController.DataBaseJDBCChatController;
import dbController.DataBaseJDBCLinkController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcAccessConfiguration {
        @Value("${spring.datasource.driver-class-name}")
        String driver = "org.postgresql.Driver";
    @Value("${spring.datasource.url}")
    String url= "jdbc:postgresql://localhost:5432/scrapper";
    @Value("spring.datasource.username}")
    String user = "postgres";
    @Value("${spring.datasource.password}")
    String password = "postgres";

    @Bean
        public DataSource dataSource() {
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName(driver);
            dataSourceBuilder.url(url);
            dataSourceBuilder.username(user);
            dataSourceBuilder.password(password);
            return dataSourceBuilder.build();
        }

        @Bean
        DataBaseJDBCLinkController linksService(DataBaseJDBCLinkController linkController) {
            return linkController;
        }

        @Bean
        DataBaseJDBCChatController chatsService(DataBaseJDBCChatController chatController) {
            return chatController;
        }
}
