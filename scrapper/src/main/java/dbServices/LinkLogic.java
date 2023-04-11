package dbServices;


import entity.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import repositories.LinkRepository;

@Service
public class LinkLogic {
    @Autowired
    private LinkRepository linkRepository;
    public Page<Link> getLinks(){
        return linkRepository.findAll();
    }
}
