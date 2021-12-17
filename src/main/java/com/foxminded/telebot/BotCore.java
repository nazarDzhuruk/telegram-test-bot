package com.foxminded.telebot;

import com.foxminded.telebot.dao.GenreDao;
import com.foxminded.telebot.dao.impl.GenreDaoImpl;
import com.foxminded.telebot.model.genre.Genres;
import com.foxminded.telebot.service.imp.MessageImpl;
import com.foxminded.telebot.source.BotConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;


public class BotCore extends TelegramLongPollingBot {
    static final String start = "/start";
    private GenreDao genreDao = GenreDaoImpl.getInstance();
    private MessageImpl message = new MessageImpl();

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_ID;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getText().equals(start)) {
                try {
                    execute(message.startMessage(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (genre(update) != null) {
                try {
                    execute(message.filmDescription(update));
                } catch (IOException | TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private Genres genre(Update update) {
        return genreDao.index().stream()
                .filter(g -> g.getGenre().equals(update.getMessage().getText())).findAny().orElse(null);
    }
}
