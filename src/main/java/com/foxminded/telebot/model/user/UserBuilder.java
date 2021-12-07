package com.foxminded.telebot.model.user;

public interface UserBuilder {
    UserBuilder setId(long id);
    UserBuilder setNickname(String nickname);
    User build();
}
