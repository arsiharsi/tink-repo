package repositories;

import entity.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface JdbcChatRepository extends CrudRepository<Chat, Long> {
    List<Chat> findAll();

}
