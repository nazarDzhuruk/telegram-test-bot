package com.foxminded.telebot.service;


import com.foxminded.telebot.dao.GenreDao;
import com.foxminded.telebot.dao.GenreDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class GenreService {
    private final GenreDao genreDao = GenreDaoImpl.getInstance();

    public List<String> filmGenres(){
        List<String> genre = new ArrayList<>();
        genreDao.index().forEach(g -> genre.add(g.getGenre()));

        return genre;
    }

    public static void main(String[] args) {
        GenreService genreService = new GenreService();
        genreService.filmGenres().forEach(s -> System.out.println(s));
    }

}
