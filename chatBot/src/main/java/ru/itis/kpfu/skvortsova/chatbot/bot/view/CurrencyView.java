package ru.itis.kpfu.skvortsova.chatbot.bot.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.ParseException;

public class CurrencyView extends BaseBotView{
    private AnchorPane pane;
    private VBox box;
    private TextArea course;
    private TextArea text;
    private Button currency;
    private Button back;

    private final EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == currency) {
                try {
                    course.setText(getBotApplication().getCurrencyService().getCurrencyRate());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                event.consume();
            }
        }
    };

    private final EventHandler<ActionEvent> backEventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == back) {
                getBotApplication().setView(getBotApplication().getCommandsView());
            }
        }
    };

    @Override
    public Parent getView() {
        if (pane == null) {
            createView();
        }
        return pane;
    }

    private void createView() {
        pane = new AnchorPane();

        box = new VBox(10);

        course = new TextArea();
        course.setEditable(false);
        course.setWrapText(true);

        currency = new Button("Get currency rate");
        currency.setOnAction(eventHandler);

        text = new TextArea();
        text.setText("Rates of three currencies");
        text.setEditable(false);
        text.setWrapText(true);

        back = new Button("Back");
        back.setOnAction(backEventHandler);

        box.getChildren().addAll(text, currency, course, back);
        pane.getChildren().addAll(box);
    }
}
