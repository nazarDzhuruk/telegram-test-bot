package com.foxminded.telebot.source;

import org.springframework.jdbc.core.JdbcTemplate;

public interface Connection {
    JdbcTemplate jdbcTemplate();
}
