package com.foxminded.telebot.service;

import com.foxminded.telebot.source.BotConfig;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageService extends DefaultAbsSender implements Message{
    private static final Message INSTANCE = new MessageService(new DefaultBotOptions());


    protected MessageService(DefaultBotOptions options) {
        super(options);
    }

    public static Message getInstance(){
        return INSTANCE;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    @Override
    public void sendMessage(String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        try{
            execute(sendMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
