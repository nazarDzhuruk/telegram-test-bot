package com.foxminded.telebot.service.imp;

import com.foxminded.telebot.dao.GenreDao;
import com.foxminded.telebot.dao.impl.GenreDaoImpl;
import com.foxminded.telebot.service.Message;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MessageImpl implements Message {
    private final GenreDao genreDao = GenreDaoImpl.getInstance();

    @Override
    public SendMessage startMessage(Update update) {
        return (SendMessage) genresToLink().keySet().stream().map(k -> SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text(k));
    }

    @Override
    public SendMessage filmDescription(Update update) throws IOException {
        return (SendMessage) filmPortrait(update).stream().map(d -> SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text(d).build());
    }

    private List<String> filmPortrait(Update update) throws IOException {
        Document document = Jsoup
                .connect("https://kinogo.film" + genresToLink().get(update.getMessage().getText())).get();
        List<String> films = new ArrayList<>();
        document.getElementsByClass("shortimg").forEach(e -> films.add(e.text()));
        return films;
    }

    private Map<String, String> genresToLink() {
        Map<String, String> genresLink = new HashMap<>();
        genreDao.index().forEach(g -> genresLink.put(g.getGenre(), g.getLink()));
        return genresLink;
    }
}
