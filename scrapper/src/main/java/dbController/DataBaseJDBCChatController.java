package dbController;

import entity.Chat;
import entity.Link;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseJDBCChatController {
    public void addChat(long chat_id, JdbcTemplate template){
        /*
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(template);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("chat_id", chat_id);

        KeyHolder keyHolder = simpleJdbcInsert
            .withTableName("chats")
            .usingColumns("chat_id")
            .usingGeneratedKeyColumns("id")
            .withoutTableColumnMetaDataAccess()
            .executeAndReturnKeyHolder(params);
        int id = keyHolder.getKey().intValue();
        System.out.println(id);
        /*
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO chats(chat_id) VALUES(?)";
        template.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setLong(1, chat_id);
            return pst;
        }, keyHolder);
        int id = keyHolder.getKey().intValue();
        System.out.println(id);
        */
        long id = getAllChats(template).size();
        template.update("INSERT INTO chats(id, chat_id) VALUES(?,?)", id, chat_id);
    }
    public void deleteChat(long chat_id, JdbcTemplate template) {
        template.update("DELETE FROM chats WHERE chat_id = ?", chat_id);
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
