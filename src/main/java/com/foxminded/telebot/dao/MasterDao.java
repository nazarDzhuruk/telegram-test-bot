package com.foxminded.telebot.dao;

import java.util.List;
import java.util.Optional;

public interface MasterDao<E> {

    void create(E e);

    Optional<E> read(long id);

    void delete(int id);

    void update(E e);

    List<E> index();
}
