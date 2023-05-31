package dbController;

import clientBeans.GHClient;
import clientBeans.SOClient;
import dao.Dao;
import dao.JpaLinkDao;
import dto_classes.GHResponse;
import dto_classes.SOResponse;
import entity.Link;
import jakarta.annotation.sql.DataSourceDefinition;
import org.apache.catalina.LifecycleState;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.List;

public class DataBaseJDBCLinkController{
    public void addLink(long chat_id, JdbcTemplate template, String link){
        Timestamp currentStamp = new Timestamp(0);
        String newLink = link;
        newLink = newLink.replace(",","/");
        if (newLink.contains("github")) {
            GHClient ghClient = new GHClient();
            GHResponse ghResponse = ghClient.fetchRepository(newLink.split("/")[3], newLink.split("/")[4]);
            currentStamp = Timestamp.valueOf(ghResponse.pushed_at().atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        }
        if (newLink.contains("stackoverflow")) {
            SOClient soClient = new SOClient();
            SOResponse soResponse = soClient.fetchQuestion(Long.parseLong(newLink.split("/")[4]));
            currentStamp = new Timestamp(soResponse.last_activity_date().getTime());
        }
        long id = getAllLinks(template).size();

        template.update("INSERT INTO links(id, chat_id, link_url, datetimestamp) VALUES(?,?,?,?)", id, chat_id, newLink,currentStamp);
    }
    public void deleteLink(long chat_id, String link, JdbcTemplate template) {
        String newLink = link.replace(",","/");
        template.update("DELETE FROM links WHERE chat_id = ? AND link_url = ?", chat_id, newLink);
    }
    public void updateLink(JdbcTemplate template, long id, Timestamp newTimestamp){
        template.update("UPDATE links SET datetimestamp = ? WHERE id = ?", newTimestamp, id);
    }
    public List<Link> getAllLinks(JdbcTemplate template){
        return template.query("SELECT * FROM links", (rs, rowNum) -> {
            Link link = new Link();
            link.setId(rs.getLong("id"));
            link.setChat_id(rs.getLong("chat_id"));
            link.setLink_url(rs.getString("link_url"));
            link.setDatetimestamp(rs.getTimestamp("datetimestamp"));
            return link;
        });
    }
    public List<Link> getCertainLinks(JdbcTemplate template, long chat_id){
        return template.query("SELECT * FROM links WHERE chat_id = "+chat_id, (rs, rowNum) -> {
            Link link = new Link();
            link.setId(rs.getLong("id"));
            link.setChat_id(rs.getLong("chat_id"));
            link.setLink_url(rs.getString("link_url"));
            link.setDatetimestamp(rs.getTimestamp("datetimestamp"));
            return link;
        });
    }
}
