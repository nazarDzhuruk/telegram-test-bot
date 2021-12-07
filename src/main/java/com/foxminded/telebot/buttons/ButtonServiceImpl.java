package com.foxminded.telebot.buttons;

import com.foxminded.telebot.service.GenreService;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonServiceImpl {

    public ReplyKeyboardMarkup setButtons(List<KeyboardRow> keyboardRows) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }

    public List<KeyboardRow> createSaveButtons(List<ButtonSaveMenu> buttons) {
        KeyboardRow keyboardRow = new KeyboardRow();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        buttons.forEach(b -> keyboardRow.add(String.valueOf(b)));

        keyboardRows.add(keyboardRow);
        return keyboardRows;
    }




    public List<KeyboardRow> rows (List<String> buttons){
        KeyboardRow keyboardRow = new KeyboardRow();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        buttons.forEach(b -> keyboardRow.add(b));
        keyboardRows.add(keyboardRow);
        return keyboardRows;
    }

    public List<KeyboardRow> createCrudButtons(List<ButtonsCrudMenu> buttons) {
        KeyboardRow keyboardRow = new KeyboardRow();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        buttons.forEach(b -> keyboardRow.add(String.valueOf(b)));

        keyboardRows.add(keyboardRow);
        return keyboardRows;
    }
}
