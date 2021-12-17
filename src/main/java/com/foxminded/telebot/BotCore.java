package com.foxminded.telebot;

import com.foxminded.telebot.dao.GenreDao;
import com.foxminded.telebot.dao.impl.GenreDaoImpl;
import com.foxminded.telebot.model.genre.Genres;
import com.foxminded.telebot.service.imp.MessageImpl;
import com.foxminded.telebot.source.BotConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
                message.startMessage(update).forEach(this::executeMessage);
            }
            if (genre(update) != null) {
                message.filmDescription(update).forEach(this::executeMessage);
            }
        }
    }

    private Genres genre(Update update) {
        return genreDao.index().stream()
                .filter(g -> g.getGenre().equals(update.getMessage().getText())).findAny().orElse(null);
    }

    private <T extends BotApiMethod> void executeMessage(T sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
