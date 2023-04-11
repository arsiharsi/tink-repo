package dbServices;


import entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import repositories.ChatRepository;

@Service
public class ChatLogic {
    @Autowired
    private ChatRepository chatRepository;
    public Page<Chat> getChats(){
        return chatRepository.findAll();
    }
}
