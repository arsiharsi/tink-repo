package scheduled;

import dbServices.LinkUpdater;
import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class LinkUpdaterScheduler {
    LinkUpdater linkUpdater = new LinkUpdater();
    @Scheduled(fixedDelay = 5000)
    public void update(){
        LogManager.getLogger().info("Sheduled");
        linkUpdater.updateLinks();
    }
}
