package com.foxminded.telebot.model.genre;

public class Genres {
    private String genre;
    private String link;

    public Genres() {
        super();
    }

    public Genres(String genre, String link) {
        this();
        this.genre = genre;
        this.link = link;
    }

    public String getGenre() {
        return genre;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "Genres{" +
                "genre='" + genre + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
