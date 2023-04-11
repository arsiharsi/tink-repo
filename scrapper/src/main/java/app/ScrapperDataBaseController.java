package app;

import dbServices.ChatLogic;
import dbServices.LinkLogic;
import entity.Chat;
import entity.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scrapper")
public class ScrapperDataBaseController {
    @Autowired
    ChatLogic chatLogic;
    @Autowired
    LinkLogic linkLogic;
    @GetMapping("/chats")
    @ResponseBody
    public Page<Chat> chats(){
        return chatLogic.getChats();
    }
    @GetMapping("/links")
    @ResponseBody
    public Page<Link> links(){
        return linkLogic.getLinks();
    }

}
