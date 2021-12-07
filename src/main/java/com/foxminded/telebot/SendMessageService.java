package com.foxminded.telebot;

import com.foxminded.telebot.buttons.ButtonSaveMenu;
import com.foxminded.telebot.buttons.ButtonServiceImpl;
import com.foxminded.telebot.buttons.ButtonsCrudMenu;
import com.foxminded.telebot.service.GenreService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class SendMessageService {
    private static final String HELLO_MESSAGE = "HELLO WORLD!";
    private static final String CREATE_MESSAGE = "ENTER THAN TAP \"CREATE\"";
    private static final String CREATED_MESSAGE = "CREATED!";

    private ButtonServiceImpl buttonService = new ButtonServiceImpl();

    public SendMessage sendMessageWithInfo(Update update) {
        SendMessage sendMessage = createMessage(update, HELLO_MESSAGE);
        ReplyKeyboardMarkup keyboardMarkup = buttonService
                .setButtons(buttonService.createCrudButtons(asList(ButtonsCrudMenu.values())));
        sendMessage.setReplyMarkup(keyboardMarkup);
        return sendMessage;
    }


    public SendMessage savedMessage(Update update) {
        return createMessage(update, CREATED_MESSAGE);
    }

    public SendMessage saveMessage(Update update) {
        SendMessage sendMessage = createMessage(update, CREATE_MESSAGE);
        ReplyKeyboardMarkup keyboardMarkup = buttonService
                .setButtons(buttonService.createSaveButtons(asList(ButtonSaveMenu.values())));
        sendMessage.setReplyMarkup(keyboardMarkup);
        return sendMessage;
    }

    public SendMessage createMessage(Update update, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText(message);
        return sendMessage;
    }
}
