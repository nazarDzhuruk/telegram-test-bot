package com.foxminded.telebot.dao;

import com.foxminded.telebot.model.genre.Genres;

import java.util.List;
import java.util.Optional;

public interface GenreDao{
    List<Genres> index();
    Optional<Genres> link(String genre);
}
