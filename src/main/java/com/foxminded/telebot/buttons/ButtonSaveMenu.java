package com.foxminded.telebot.buttons;

public enum ButtonSaveMenu {
    SAVE("SAVE"),
    CANCEL("CANCEL");

    private final String button;

    ButtonSaveMenu(String button) {
        this.button = button;
    }
}