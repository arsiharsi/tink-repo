package dbServices;

import clientBeans.BotClient;
import clientBeans.GHClient;
import clientBeans.SOClient;
import dbController.DataBaseJDBCLinkController;
import dto_classes.DataClass;
import dto_classes.GHResponse;
import dto_classes.SOResponse;
import entity.Link;
import org.apache.catalina.LifecycleState;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.List;

public class LinkUpdater {
    BotClient botClient = new BotClient();
    DataBaseJDBCLinkController linkController = new DataBaseJDBCLinkController();
    JdbcTemplate template = new JdbcTemplate();
    public void updateLinks(){
        List<Link> links = linkController.getAllLinks(template);
        for (int i = 0; i < links.size(); i++){
            Link link = links.get(i);
            String URL = link.getLink();
            if (URL.contains("github")) {
                GHClient ghClient = new GHClient();
                GHResponse ghResponse = ghClient.fetchRepository(URL.split("/")[3], URL.split("/")[4]);
                Timestamp currentStamp = Timestamp.valueOf(ghResponse.updatedAt().atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
                if (!currentStamp.equals(link.getDatetimestamp())) {
                    DataClass dataClass = new DataClass(link.getId(), link.getLink(),"GH Updated",link.getChatId());
                    botClient.sendUpdate(dataClass);
                    linkController.updateLink(template,link.getId(), currentStamp);
                }
            }
            if (URL.contains("stackoverflow")) {
                SOClient soClient = new SOClient();
                SOResponse soResponse = soClient.fetchQuestion(Long.parseLong(URL.split("/")[4]));
                Timestamp currentStamp = Timestamp.valueOf(soResponse.updatedAt().atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
                if (!soResponse.updatedAt().equals(link.getDatetimestamp())) {
                    DataClass dataClass = new DataClass(link.getId(), link.getLink(),"SO Updated",link.getChatId());
                    botClient.sendUpdate(dataClass);
                    linkController.updateLink(template,link.getId(), currentStamp);
                }
            }

        }
    }

}
