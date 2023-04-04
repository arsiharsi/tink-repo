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
        TgBotMethods tgBotMethods = new TgBotMethods();
        tgBotMethods.setBot(bot);
        tgBotMethods.setUpdateListener();


        BotCommand[] botCommands = {new BotCommand("help","вывести окно с командами"),
        new BotCommand("track","начать отслеживание ссылки"),
        new BotCommand("untrack", "прекратить отслеживание ссылки"),
        new BotCommand("list", "показать список отслеживаемых ссылок")};
        bot.execute(new SetMyCommands(botCommands));

    }
}

