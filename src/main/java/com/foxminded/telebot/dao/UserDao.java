package com.foxminded.telebot.dao;

import com.foxminded.telebot.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserDao<E>{
    void create(User user);

    Optional<E> read(long id);

    void delete(int id);

    void update(User user);

    List<E> index();
}
