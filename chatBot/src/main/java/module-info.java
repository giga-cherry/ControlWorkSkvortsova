module ru.itis.kpfu.skvortsova.chatbot {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    requires com.google.gson;

    opens ru.itis.kpfu.skvortsova.chatbot.bot to javafx.fxml;
    exports ru.itis.kpfu.skvortsova.chatbot.bot;
}