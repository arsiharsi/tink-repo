package app;

import configuration.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import services.ScrapperQueueListener;
import tgBotClasses.TgBot;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
	public static void main(String[] args) {
	      var ctx = SpringApplication.run(BotApplication.class, args);
	      ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
		  TgBot bot = new TgBot();
		  bot.startBot();
		  BotController.bot = bot.getTgBotMethods();
		  ScrapperQueueListener.botMethods = bot.getTgBotMethods();
	      System.out.println(config);
	  }
}
