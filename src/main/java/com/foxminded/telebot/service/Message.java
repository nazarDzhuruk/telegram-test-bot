package com.foxminded.telebot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public interface Message {
    SendMessage startMessage(Update update);
    SendMessage filmDescription(Update update) throws IOException;
}
