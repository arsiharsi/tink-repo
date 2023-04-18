package dbController;

import entity.Link;
import org.apache.catalina.LifecycleState;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DataBaseJDBCLinkController {
    public void addLink(long chat_id, JdbcTemplate template, Link link){
        template.update("INSERT INTO links(id, chat_id, link_url) VALUES(?,?,?)", link.getId(), link.getChatId(), link.getLink());
    }
    public void deleteLink(Link link, JdbcTemplate template) {
        template.update("DELETE FROM links WHERE id = ?", link.getId());
    }
    public List<Link> getAllLinks(JdbcTemplate template, long chat_id){
        return template.query("SELECT * FROM links WHERE chat_id = "+chat_id, (rs, rowNum) -> {
            Link link = new Link();
            link.setId(rs.getLong("id"));
            link.setChat_id(rs.getLong("chat_id"));
            link.setLink_url(rs.getString("link_url"));
            return link;
        });
    }
}
