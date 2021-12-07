package com.foxminded.telebot.source;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class Database implements Connection{
    private static final Connection INSTANCE = new Database();

    private static final String URL = "jdbc:postgresql://localhost:5432/telegram";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USERNAME = "nazardzh";
    private static final String PASSWORD = "";

    private Database() {
    }

    public static Connection getInstance(){
        return INSTANCE;
    }

    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }


    @Override
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
