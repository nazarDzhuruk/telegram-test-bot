package com.foxminded.telebot.service;

import com.foxminded.telebot.dao.UserDao;
import com.foxminded.telebot.dao.UserDaoImpl;
import com.foxminded.telebot.model.user.User;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class UserService {
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final Message message = MessageService.getInstance();

    public void createUser(Update update) {
        long id = update.getMessage().getChatId();
        User user = userDao.index().stream().filter(u -> u.getId() == id).findAny().orElse(null);
        if (user == null) {
            String nickname = update.getMessage().getFrom().getUserName();
            User user1 = new User(id, nickname);
            userDao.create(user1);
        } else {
            String msg = "Hello " + update.getMessage().getFrom().getUserName();
            message.sendMessage(msg);
        }
    }

    public List<User> showIndex(){
        return userDao.index();
    }
}
