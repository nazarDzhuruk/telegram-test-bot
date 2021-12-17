package com.foxminded.telebot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;

public interface Message {
    List<SendMessage> startMessage(Update update);
    List<SendMessage> filmDescription(Update update) throws IOException;
}
