package dbController;

import entity.Chat;
import entity.Link;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DataBaseJDBCChatController {
    public void addChat(long chat_id, JdbcTemplate template, Chat chat){
        template.update("INSERT INTO chats(id, chat_id) VALUES(?,?)", chat.getId(), chat.getChatId());
    }
    public void deleteChat(Chat chat, JdbcTemplate template) {
        template.update("DELETE FROM chats WHERE id = ?", chat.getId());
    }
    public List<Chat> getAllChats(JdbcTemplate template){
        return template.query("SELECT * FROM chats", (rs, rowNum) -> {
            Chat chat = new Chat();
            chat.setId(rs.getLong("id"));
            chat.setChat_id(rs.getLong("chat_id"));
            return chat;
        });
    }
}
