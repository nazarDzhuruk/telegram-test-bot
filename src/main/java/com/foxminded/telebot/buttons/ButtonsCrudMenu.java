package com.foxminded.telebot.buttons;

public enum ButtonsCrudMenu {
    CREATE("CREATE"),
    READ("READ"),
    DELETE("DELETE"),
    UPDATE("UPDATE");

    private final String button;

    ButtonsCrudMenu(String button) {
        this.button = button;
    }
}
