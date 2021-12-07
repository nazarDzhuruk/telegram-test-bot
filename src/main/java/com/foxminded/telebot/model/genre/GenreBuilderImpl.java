package com.foxminded.telebot.model.genre;

public class GenreBuilderImpl implements GenreBuilder{
    private String genre;
    private String link;

    @Override
    public GenreBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    @Override
    public GenreBuilder setLink(String link) {
        this.link = link;
        return this;
    }

    @Override
    public Genres build() {
        Genres genres = new Genres(genre, link);
        return genres;
    }
}
