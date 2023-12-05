package ru.itis.kpfu.skvortsova.chatbot.bot.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class StartView extends BaseBotView {

    private AnchorPane pane;
    private Button start;

    @Override
    public Parent getView() {
        if (pane == null) {
            createView();
        }
        return pane;
    }

    private final EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == start) {
                getBotApplication().setView(getBotApplication().getCommandsView());
            }
        }
    };

    private void createView() {
        pane = new AnchorPane();
        start = new Button("Start bot");
        start.setOnAction(eventHandler);

        AnchorPane.setTopAnchor(start, 10.0);
        AnchorPane.setLeftAnchor(start, 10.0);
        AnchorPane.setRightAnchor(start, 10.0);

        pane.getChildren().addAll(start);
    }
}
