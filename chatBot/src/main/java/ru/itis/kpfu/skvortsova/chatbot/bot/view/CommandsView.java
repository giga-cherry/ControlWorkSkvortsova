package ru.itis.kpfu.skvortsova.chatbot.bot.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class CommandsView extends BaseBotView {
    private VBox box;
    private Button weather;
    private Button end;
    private Button currency;
    private Button chat;
    private AnchorPane pane;

    @Override
    public Parent getView() {
        if (pane == null) {
            createView();
        }
        return pane;
    }

    private final EventHandler<ActionEvent> weatherEventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == weather) {
                getBotApplication().setView(getBotApplication().getWeatherView());
            }
        }
    };

    private final EventHandler<ActionEvent> currencyEventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == currency) {
                getBotApplication().setView(getBotApplication().getCurrencyView());
            }
        }
    };

    private final EventHandler<ActionEvent> chatEventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == chat) {
                getBotApplication().setView(getBotApplication().getUserConfigView());
            }
        }
    };

    private final EventHandler<ActionEvent> endEventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == end) {
                getBotApplication().end();
            }
        }
    };

    private void createView() {
        pane = new AnchorPane();

        box = new VBox(10);

        weather = new Button("Get forecast");
        weather.setOnAction(weatherEventHandler);
        currency = new Button("Get currency rate");
        currency.setOnAction(currencyEventHandler);
        chat = new Button("Go to chat");
        chat.setOnAction(chatEventHandler);
        end = new Button("Exit");
        end.setOnAction(endEventHandler);

        box.getChildren().addAll(weather, currency, chat, end);
        pane.getChildren().addAll(box);
    }
}
