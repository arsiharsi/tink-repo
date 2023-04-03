package tgBotClasses;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.SendResponse;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.AbstractFutureOrPresentJavaTimeValidator;
//"6201413186:AAGMfU2EOz8dBCRjRd0FqSGsFXu96gOi6kA"
//"#{bot.token}"
import java.util.List;

public class TgBot {
    TelegramBot bot = new TelegramBot("6201413186:AAGMfU2EOz8dBCRjRd0FqSGsFXu96gOi6kA");
    public void startBot(){
        bot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(List<Update> updates) {
                updates.forEach(update ->{
                            if (update.message()==null){
                                return;
                            }
                            String msg = update.message().text();
                            System.out.println(msg);
                            String helpMsg = "/help - вывести окно с командами\n" +
                                    "/track - начать отслеживание ссылки\n" +
                                    "/untrack - прекратить отслеживание ссылки\n" +
                                    "/list - показать список отслеживаемых ссылок";
                            switch (msg){
                                case "/help" -> bot.execute(new SendMessage(update.message().chat().id(), helpMsg));
                                case "/start" -> bot.execute(new SendMessage(update.message().chat().id(), "Запущен"));
                                case "/track" -> bot.execute(new SendMessage(update.message().chat().id(), "затречить"));
                                case "/untrack" -> bot.execute(new SendMessage(update.message().chat().id(), "не тречить"));
                                case "/list" -> bot.execute(new SendMessage(update.message().chat().id(), "список"));
                                default -> bot.execute(new SendMessage(update.message().chat().id(), "/help - для вызова списка команд"));
                            }
                        }

                );



                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }

        });

        BotCommand[] botCommands = {new BotCommand("help","вывести окно с командами"),
        new BotCommand("track","начать отслеживание ссылки"),
        new BotCommand("untrack", "прекратить отслеживание ссылки"),
        new BotCommand("list", "показать список отслеживаемых ссылок")};
        bot.execute(new SetMyCommands(botCommands));

    }
}

