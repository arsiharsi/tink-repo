package dbServices;


import entity.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import repositories.JdbcLinkRepository;

import java.util.List;

@Service
public class LinkLogic {
    @Autowired
    private JdbcLinkRepository linkRepository;
    public List<Link> getLinks(){
        return linkRepository.findAll();
    }
}
