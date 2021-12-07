package com.foxminded.telebot.model.user;

public class User {
    private long id;
    private String nickname;

    public User(){
        super();
    }

    public User(long id, String nickname) {
        this();
        this.id = id;
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
