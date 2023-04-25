package dao;

import entity.Chat;
import entity.Link;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;


@Service
public class JpaChatDao implements Dao<Chat>{
    private EntityManager entityManager;

    @Override
    public Optional<Chat> get(long id) {
        return Optional.ofNullable(entityManager.find(Chat.class, id));
    }

    @Override
    public List<Chat> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM chats e");
        return query.getResultList();
    }

    @Override
    public void save(Chat chat) {
        executeInsideTransaction(entityManager -> entityManager.persist(chat));
    }

    @Override
    public void update(Chat chat, String[] params) {
        chat.setId(Objects.requireNonNull(Long.parseLong(params[0]), "Id cannot be null"));
        chat.setChat_id(Objects.requireNonNull(Long.parseLong(params[1]), "Chat_id cannot be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(chat));
    }

    @Override
    public void delete(Chat chat) {
        executeInsideTransaction(entityManager -> entityManager.remove(chat));
    }
    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
