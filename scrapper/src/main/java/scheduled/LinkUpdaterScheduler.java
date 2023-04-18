package scheduled;

import dbServices.LinkUpdater;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class LinkUpdaterScheduler {
    @Autowired
    LinkUpdater linkUpdater = new LinkUpdater();
    @Scheduled(fixedDelayString = "#{app.scheduler.interval}")
    public void update(){
        LogManager.getLogger().info("Sheduled");
        linkUpdater.updateLinks();
    }
}
