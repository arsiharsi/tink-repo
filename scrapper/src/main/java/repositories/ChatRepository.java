package repositories;

import entity.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface ChatRepository extends Repository<Chat, Long> {
    Page<Chat> findAll();

}
