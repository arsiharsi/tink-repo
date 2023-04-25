package repositories;

import entity.Chat;
import entity.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface JdbcLinkRepository extends Repository<Link, Long> {
    List<Link> findAll();
}
