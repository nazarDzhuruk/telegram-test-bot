package com.foxminded.telebot.model.genre;

public interface GenreBuilder {
    GenreBuilder setGenre(String genre);
    GenreBuilder setLink(String link);
    Genres build();
}
