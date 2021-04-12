package be.joengenduvel.codenature.app.config;

import be.joengenduvel.codenature.game.GameManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.ZonedDateTime;

@Configuration
@AllArgsConstructor
public class CleanupConfig {

    private final GameManager gameManager;

    @Scheduled(cron = "1****")
    void cleanOldGames(){
        ZonedDateTime twoHoursAgo = ZonedDateTime.now().minusHours(2);
        gameManager.cleanGamesOlderThan(twoHoursAgo);
    }
}
