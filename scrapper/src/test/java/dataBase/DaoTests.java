package dataBase;


import dao.JpaChatDao;
import dao.JpaLinkDao;
import entity.Chat;
import entity.Link;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class DaoTests {
    @Autowired
    private JpaLinkDao linkRepository;
    @Autowired
    private JpaChatDao chatRepository;
    @Autowired


    @Transactional
    @Rollback
    void addLinkTest() {
        Link newLink = new Link();
        newLink.setId(0);
        newLink.setChat_id(0);
        newLink.setLink_url("asda");
        linkRepository.save(newLink);
        Assert.assertTrue(linkRepository.get(0).equals(newLink));
    }

    @Transactional
    @Rollback
    void removeLinkTest() {
        Link newLink = new Link();
        newLink.setId(0);
        newLink.setChat_id(0);
        newLink.setLink_url("asda");
        linkRepository.save(newLink);
        linkRepository.delete(newLink);
        Assert.assertNull(linkRepository.get(0));
    }
    @Transactional
    @Rollback
    void addChatTest() {
        Chat newChat = new Chat();
        newChat.setId(0);
        newChat.setChat_id(0);
        chatRepository.save(newChat);
        Assert.assertTrue(chatRepository.get(0).equals(newChat));
    }

    @Transactional
    @Rollback
    void removeChatTest() {
        Chat newChat = new Chat();
        newChat.setId(0);
        newChat.setChat_id(0);
        chatRepository.save(newChat);
        chatRepository.delete(newChat);
        Assert.assertNull(chatRepository.get(0));
    }
}
