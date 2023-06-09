package configuration;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.annotation.Validated;
import reactor.core.scheduler.Scheduler;

import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "app")
@EnableScheduling
public record ApplicationConfig(@NotNull String test, Scheduler scheduler, boolean useQueue) {
    @Value("${app.scheduler.interval}")
    private static Duration schedulerInterval;
    static AccessType databaseAccessType;
}

