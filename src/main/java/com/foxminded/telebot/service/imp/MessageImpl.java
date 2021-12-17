package com.foxminded.telebot.service.imp;

import com.foxminded.telebot.dao.GenreDao;
import com.foxminded.telebot.dao.impl.GenreDaoImpl;
import com.foxminded.telebot.service.Message;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MessageImpl implements Message {
    private final GenreDao genreDao = GenreDaoImpl.getInstance();
    private static final String URL = "https://kinogo.film";
    private static final String CLASS_NAME = "shortimg";

    @Override
    public List<SendMessage> startMessage(Update update) {
        return genresToLink().keySet().stream().map(k -> SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text(k).build()).collect(Collectors.toList());
    }

    @Override
    public List<SendMessage> filmDescription(Update update) {
        List<SendMessage> sendMessage = new ArrayList<>();
        try {
            sendMessage = filmPortrait(update).stream().map(d -> SendMessage.builder()
                    .chatId(update.getMessage().getChatId().toString())
                    .text(d).build()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sendMessage;
    }

    private List<String> filmPortrait(Update update) throws IOException {
        Document document = Jsoup
                .connect(URL + genresToLink().get(update.getMessage().getText())).get();
        return document.getElementsByClass(CLASS_NAME).stream().map(Element::text).toList();
    }

    private Map<String, String> genresToLink() {
        Map<String, String> genresLink = new LinkedHashMap<>();
        genreDao.index().forEach(g -> genresLink.put(g.getGenre(), g.getLink()));
        return genresLink;
    }
}