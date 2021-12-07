package com.foxminded.telebot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Message {
    void sendMessage(String message);
}
