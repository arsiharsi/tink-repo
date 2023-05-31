package dbServices;

import clientBeans.BotClient;
import clientBeans.GHClient;
import clientBeans.SOClient;
import configuration.JdbcAccessConfiguration;
import services.ScrapperQueueProducer;
import dbController.DataBaseJDBCLinkController;
import dto_classes.DataClass;
import dto_classes.GHResponse;
import dto_classes.SOResponse;
import entity.Link;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.List;

public class LinkUpdater {
    @Value("$app.useQueue")
    boolean usingQueue;
    BotClient botClient = new BotClient();
    ScrapperQueueProducer scrapperQueueProducer = new ScrapperQueueProducer();
    DataBaseJDBCLinkController linkController = new DataBaseJDBCLinkController();
    DataSource dataSource = new JdbcAccessConfiguration().dataSource();
    JdbcTemplate template = new JdbcTemplate(dataSource);
    public void updateLinks(){
        List<Link> links = linkController.getAllLinks(template);
        for (int i = 0; i < links.size(); i++){
            Link link = links.get(i);
            String URL = link.getLink();
            if (URL.contains("github")) {
                GHClient ghClient = new GHClient();
                GHResponse ghResponse = ghClient.fetchRepository(URL.split("/")[3], URL.split("/")[4]);
                Timestamp currentStamp = Timestamp.valueOf(ghResponse.pushed_at().atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
                if (!currentStamp.equals(link.getDatetimestamp())) {
                    DataClass dataClass = new DataClass(link.getId(), link.getLink(),"GH Updated",link.getChatId());
                    if (!usingQueue) {
                        botClient.sendUpdate(dataClass);
                    }
                    else {
                        scrapperQueueProducer.send(dataClass);
                    }
                    linkController.updateLink(template,link.getId(), currentStamp);
                }
            }
            if (URL.contains("stackoverflow")) {
                SOClient soClient = new SOClient();
                SOResponse soResponse = soClient.fetchQuestion(Long.parseLong(URL.split("/")[4]));
                Timestamp currentStamp = new Timestamp(soResponse.last_activity_date().getTime());
                if (!soResponse.last_activity_date().equals(link.getDatetimestamp())) {
                    DataClass dataClass = new DataClass(link.getId(), link.getLink(),"SO Updated",link.getChatId());
                    if (!usingQueue) {
                        botClient.sendUpdate(dataClass);
                    }
                    else {
                        scrapperQueueProducer.send(dataClass);
                    }
                    linkController.updateLink(template,link.getId(), currentStamp);
                }
            }

        }
    }

}
