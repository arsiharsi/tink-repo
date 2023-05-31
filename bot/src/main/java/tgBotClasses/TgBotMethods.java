package tgBotClasses;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import dto_classes.Link;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Counter;
import scrapperClients.ChatClient;
import scrapperClients.LinkClient;

import java.util.List;

public class TgBotMethods {
    private TelegramBot bot;
    private boolean hasList = true;
    private ChatClient chatClient = new ChatClient();
    private LinkClient linkClient = new LinkClient();
    Counter messagesCounter = Metrics.counter("processed_messages", "application", "bot");
    public void setUpdateListener(){
        this.bot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(List<Update> updates) {
                updates.forEach(update ->{
                            if (update.message()==null){
                                return;
                            }
                            String msg = update.message().text();
                            long id = update.message().chat().id();
                            System.out.println(id);
                            System.out.println(msg);
                            doAction(msg, update, id);
                            messagesCounter.increment();
                        }
                );



                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }

        });
    }
    public void setBot(TelegramBot bot){
        this.bot = bot;
    }
    private void doAction(String msg, Update update, long id){
        bot.execute(new SendMessage(update.message().chat().id(), getResponse(msg, id)));

    }
    public void sendMessage(long chatId, String msg){
        bot.execute(new SendMessage(chatId, msg));
    }
    public String getResponse(String  msg, long id){
        String helpMsg = "/help - вывести окно с командами\n" +
                "/track - начать отслеживание ссылки\n" +
                "/untrack - прекратить отслеживание ссылки\n" +
                "/list - показать список отслеживаемых ссылок";
        switch (msg){
            case "/help":   return helpMsg;
            case "/start": {
                chatClient.addChat(id);
                return "Запущен";}
            case "/track": return  "Отправьте ссылку для отслеживания";
            case "/untrack": return "Отправьте ссылку для остановки отслеживания";
            case "/list": {
                Link[] links = linkClient.getLink(id);
                String response = "";
                for (int i = 0; i < links.length; i++){
                    response += links[i].link()+"\n";
                }
                return  (!response.equals("")) ? response:"У вас нет ссылок";}
            default: {
                //System.out.println(msg.contains("https://github.com/"));
                //System.out.println(countSymbols(msg,"/")>=4);
                if ((msg.contains("https://github.com/") && countSymbols(msg,"/")>=4) || msg.contains("https://stackoverflow.com/questions/")){
                    Link[] links = linkClient.getLink(id);
                    for (int i = 0; i < links.length; i++){
                        if (links[i].link()!=null) {
                            if (links[i].link().equals(msg)) {
                                linkClient.deleteLink(id, msg.replace("/", ","));
                                return "Ссылка удалена";
                            }
                        }
                    }
                    linkClient.addLink(id, msg.replace("/",","));
                    return "Ссылка добавлена";
                }else {
                    return "/help - для вызова списка команд";
                }
            }
        }
    }
    private int countSymbols(String string, String substring){
        int i = string.length() - string.replace(substring, "").length();
        return i;
    }
    public void setHasList(boolean inHasList){
        this.hasList = inHasList;
    }
}
