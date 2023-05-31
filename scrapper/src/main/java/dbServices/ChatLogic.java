package dbServices;


import entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import repositories.JdbcChatRepository;

import java.util.List;

@Service
public class ChatLogic {
    @Autowired
    private JdbcChatRepository chatRepository;
    public List<Chat> getChats(){
        return chatRepository.findAll();
    }
}
