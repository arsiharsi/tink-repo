package app;

import configuration.JdbcAccessConfiguration;
import dbController.DataBaseJDBCChatController;
import dbController.DataBaseJDBCLinkController;
import dbServices.ChatLogic;
import dbServices.LinkLogic;
import entity.Chat;
import entity.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("/scrapper")
public class ScrapperDataBaseController {

    ChatLogic chatLogic;

    LinkLogic linkLogic;

    DataSource dataSource = new JdbcAccessConfiguration().dataSource();
    JdbcTemplate template = new JdbcTemplate(dataSource);
    DataBaseJDBCChatController dataBaseJDBCChatController = new DataBaseJDBCChatController();
    DataBaseJDBCLinkController dataBaseJDBCLinkController = new DataBaseJDBCLinkController();
    @GetMapping("/chats")
    @ResponseBody
    public List<Chat> chats(){
        List<Chat> list = dataBaseJDBCChatController.getAllChats(template);
        return list;
    }
    @GetMapping("/links")
    @ResponseBody
    public List<Link> links(){
        List<Link> list = dataBaseJDBCLinkController.getAllLinks(template);
        return list;
    }

}
