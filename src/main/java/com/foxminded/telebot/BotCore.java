package com.foxminded.telebot;

import com.foxminded.telebot.dao.GenreDao;
import com.foxminded.telebot.dao.GenreDaoImpl;
import com.foxminded.telebot.model.genre.Genres;
import com.foxminded.telebot.service.UserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BotCore extends TelegramLongPollingBot {
    private static final String BOT_TOKEN = "5026460657:AAFQbKA9MPt7fA4F8z31N9-CLycPbhq2P2Q";
    private static final String BOT_NAME = "@nzik_bot";
    static final String start = "/start";
    static final String create = "create";
    static final String save = "save";
    static final String read = "read";
    private
    SendMessageService sendMessageService = new SendMessageService();
    UserService userService = new UserService();
    private GenreDao genreDao = GenreDaoImpl.getInstance();


    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getText().equals(start)) {
                genres().forEach(g -> executeMessage(SendMessage.builder()
                        .chatId(update.getMessage().getChatId().toString())
                        .text(g).build()));
            }
            if (genre(update) != null) {
                try {
                    Document document = Jsoup.connect("https://gidonline.io/" + genre(update).getLink()).get();
                    Elements link = document.select("a.mainlink");
                    List<String> films = link.eachAttr("href");
                    films.forEach(f -> executeMessage(SendMessage.builder()
                            .chatId(update.getMessage().getChatId().toString())
                            .text(f).build()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Genres genre(Update update) {
        return genreDao.index().stream()
                .filter(g -> g.getGenre().equals(update.getMessage().getText())).findAny().orElse(null);
    }


    private List<String> genres() {
        genreDao = new GenreDaoImpl();
        List<String> genres = new ArrayList<>();
        genreDao.index().forEach(g -> genres.add(g.getGenre()));
        return genres;
    }

    private <T extends BotApiMethod> void executeMessage(T sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
