package com.foxminded.telebot.dao.impl;

import com.foxminded.telebot.dao.GenreDao;
import com.foxminded.telebot.model.genre.GenreMapper;
import com.foxminded.telebot.model.genre.Genres;
import com.foxminded.telebot.source.Connection;
import com.foxminded.telebot.source.Database;

import java.util.List;
import java.util.Optional;

public class GenreDaoImpl implements GenreDao {
    private final Connection connection = Database.getInstance();
    private static final GenreDao INSTANCE = new GenreDaoImpl();

    public GenreDaoImpl() {
    }

    public static GenreDao getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Genres> index() {
        String sql = "SELECT * FROM film_genre";
        return connection.jdbcTemplate().query(sql, new GenreMapper());
    }

    @Override
    public Optional<Genres> link(String genre) {
        String sql = "SELECT link FROM film_genre WHERE genre=?";
        Genres genres = connection.jdbcTemplate().queryForObject(sql, new GenreMapper(), genre);
        return Optional.ofNullable(genres);
    }

}
