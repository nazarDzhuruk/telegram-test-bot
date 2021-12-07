package com.foxminded.telebot.model.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BuilderImpl()
                .setId(rs.getInt("id"))
                .setNickname(rs.getString("nickname")).build();
    }
}
