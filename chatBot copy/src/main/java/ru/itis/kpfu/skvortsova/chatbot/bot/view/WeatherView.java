package ru.itis.kpfu.skvortsova.chatbot.bot.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

public class WeatherView extends BaseBotView{

    private AnchorPane pane;
    private VBox box;
    private TextArea city;
    private TextArea weather;

    private Button back;

    private final EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                String req = city.getText();

                // send message
                getBotApplication().getForecastService().getCityForecast(req);

                weather.setText(getBotApplication().getForecastService().getCityForecast(req));

                city.clear();
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

        weather = new TextArea();
        weather.setEditable(false);
        weather.setWrapText(true);

        city = new TextArea();
        city.setMaxHeight(50);
        city.addEventHandler(KEY_PRESSED, eventHandler);

        back = new Button("Back");
        back.setOnAction(backEventHandler);

        box.getChildren().addAll(city, weather, back);
        pane.getChildren().addAll(box);
    }
}
