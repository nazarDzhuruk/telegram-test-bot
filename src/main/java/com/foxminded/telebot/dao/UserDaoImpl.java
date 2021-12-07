package com.foxminded.telebot.dao;

import com.foxminded.telebot.model.user.User;
import com.foxminded.telebot.model.user.UserMapper;
import com.foxminded.telebot.source.Connection;
import com.foxminded.telebot.source.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final UserDao INSTANCE = new UserDaoImpl();

    private final Connection connection = Database.getInstance();
    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private UserDaoImpl() {
    }

    @Override
    public void create(User user) {
        logger.debug("Add user status: in progress... ");
        if (user == null) {
            String exception = "Add user status: student is null!";
            logger.warn(exception);
        }
        String sql = "INSERT INTO chat_user VALUES(?,?)";
        try {
            connection.jdbcTemplate().update(sql, user.getId(), user.getNickname());
        } catch (DuplicateKeyException e) {
            String duplicate = "User already exist";
            logger.warn(duplicate);
        }
        logger.info("Add user status: added successfully. ");
    }

    @Override
    public Optional<User> read(long id) {
        logger.debug("Read from database status: in progress... ");
        String sql = "SELECT * FROM chat_user WHERE id=?";
        User user = null;
        try {
            user = connection.jdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<>(), id);
        } catch (EmptyResultDataAccessException e) {
            String error = "Student not found";
            logger.warn(error);
        } catch (DataAccessException exception) {
            String throwE = "Unable to get";
            logger.warn(throwE + id);
        }
        logger.info("Success read file");
        return Optional.ofNullable(user);
    }

    @Override
    public void delete(int id) {
        logger.debug("Deleting...");
        String sql = "DELETE FROM chat_user WHERE id=?";
        try {
            connection.jdbcTemplate().update(sql, id);
        } catch (BadSqlGrammarException e) {
            String msg = "user does not exist";
            logger.warn(msg);
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> index() {
        String sql = "SELECT * FROM chat_user";
        return connection.jdbcTemplate().query(sql, new UserMapper());
    }
}
