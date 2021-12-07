package com.foxminded.telebot.model.genre;

import com.foxminded.telebot.model.user.BuilderImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genres> {
    @Override
    public Genres mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GenreBuilderImpl()
                .setGenre(rs.getString("genre"))
                .setLink(rs.getString("link")).build();
    }
}
