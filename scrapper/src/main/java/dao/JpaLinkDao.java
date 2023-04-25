package dao;

import entity.Link;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;


@Service
public class JpaLinkDao implements Dao<Link>{
    private EntityManager entityManager;

    @Override
    public Optional<Link> get(long id) {
        return Optional.ofNullable(entityManager.find(Link.class, id));
    }

    @Override
    public List<Link> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM links e");
        return query.getResultList();
    }

    @Override
    public void save(Link link) {
        executeInsideTransaction(entityManager -> entityManager.persist(link));
    }

    @Override
    public void update(Link link, String[] params) {
        link.setId(Objects.requireNonNull(Long.parseLong(params[0]), "Id cannot be null"));
        link.setChat_id(Objects.requireNonNull(Long.parseLong(params[1]), "Chat_id cannot be null"));
        link.setLink_url(Objects.requireNonNull(params[2], "Link cannot be null"));
        link.setDatetimestamp(Objects.requireNonNull(Timestamp.valueOf(params[3]), "Link cannot be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(link));
    }

    @Override
    public void delete(Link link) {
        executeInsideTransaction(entityManager -> entityManager.remove(link));
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
