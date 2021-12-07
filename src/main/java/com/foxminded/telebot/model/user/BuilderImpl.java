package com.foxminded.telebot.model.user;

public class BuilderImpl implements UserBuilder{
    private String nickname;
    private long id;


    @Override
    public UserBuilder setId(long id) {
        this.id = id;
        return this;
    }

    @Override
    public UserBuilder setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    @Override
    public User build() {
        User user = new User(id, nickname);
        return user;
    }
}
