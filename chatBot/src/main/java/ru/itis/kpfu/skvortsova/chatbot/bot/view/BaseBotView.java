package ru.itis.kpfu.skvortsova.chatbot.bot.view;

import javafx.scene.Parent;
import ru.itis.kpfu.skvortsova.chatbot.bot.BotApplication;

public abstract class BaseBotView {

    private static BotApplication botApplication;

    public static ru.itis.kpfu.skvortsova.chatbot.bot.BotApplication getBotApplication() {
        if (botApplication != null) {
            return botApplication;
        }
        throw new RuntimeException("No app in base view");
    }

    public static void setBotApplication(BotApplication botApplication) {
        BaseBotView.botApplication =botApplication;
    }

    public abstract Parent getView();
}
