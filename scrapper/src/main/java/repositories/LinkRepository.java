package repositories;

import entity.Chat;
import entity.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface LinkRepository extends Repository<Link, Long> {
    Page<Link> findAll();
}
