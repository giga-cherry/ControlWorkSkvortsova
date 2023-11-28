package ru.itis.kpfu.skvortsova.chatbot.chat.view;

import ru.itis.kpfu.skvortsova.chatbot.bot.BotApplication;
import javafx.scene.Parent;

public abstract class BaseView {

    private static BotApplication chatApplication;

    public static BotApplication getChatApplication() {
        if (chatApplication != null) {
            return chatApplication;
        }
        throw new RuntimeException("No app in base view");
    }

    public static void setChatApplication(BotApplication chatApplication) {
        BaseView.chatApplication =chatApplication;
    }

    public abstract Parent getView();
}
