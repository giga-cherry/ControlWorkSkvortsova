package ru.itis.kpfu.skvortsova.chatbot.bot.view;

import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ru.itis.kpfu.skvortsova.chatbot.bot.model.Apples;
import ru.itis.kpfu.skvortsova.chatbot.bot.model.GameApple;
import ru.itis.kpfu.skvortsova.chatbot.bot.model.GameRectangle;
import ru.itis.kpfu.skvortsova.chatbot.bot.model.PoisonApple;

public class GameView extends BaseBotView{

    public static final int WIDTH=350;
//    x
    public static final int LENGTH=250;
    private static GameApple apple;
    private static PoisonApple poisonApple;
    private static GameRectangle player;
    private static GameUnit gameUnit;
    private static Circle circle;
    private static Circle poison;
    private AnchorPane pane;
    private static Label score;
    private static int scoreNum;

    @Override
    public Parent getView() {
        if (pane == null) {
            createView();
        }
        return pane;
    }

    private void createView() {
        pane = new AnchorPane();

        score = new Label();
        score.setWrapText(true);
        scoreNum=0;
        score.setText("Score: 0");

        AnchorPane.setTopAnchor(score, 10.0);
        AnchorPane.setLeftAnchor(score, 10.0);
        AnchorPane.setRightAnchor(score, 10.0);

        player=new GameRectangle();
        poisonApple= new PoisonApple(player.getX()[0], player.getX()[1], player.getY()[0], player.getY()[1], WIDTH, LENGTH);
        apple=new GameApple(player.getX()[0], player.getX()[1], player.getY()[0], player.getY()[1], WIDTH, LENGTH, poisonApple.getX(), poisonApple.getY());


        circle = new Circle(apple.getX(),apple.getY(), apple.getRADIUS(), Paint.valueOf("red"));
        poison = new Circle(poisonApple.getX(),poisonApple.getY(), poisonApple.getRADIUS(), Paint.valueOf("green"));

        gameUnit = new GameUnit(player.get1(), player.get2(), player.getSize(), player.getSize());
        getBotApplication().getScene().setOnKeyPressed(gameUnit);
        pane.getChildren().addAll(gameUnit, circle, score,poison);
    }

    private static void gameEnd(){
        getBotApplication().getScene().setOnKeyPressed(null);
        score.setText("Score: "+ scoreNum+", game ended");
    }

    private static void checkEat(){
        if (isEaten(apple)) {
            player.eat();
            gameUnit.setWidth(player.getSize());
            gameUnit.setHeight(player.getSize());
            scoreNum++;
            score.setText("Score: "+ scoreNum);
            if (apple.generatePosition(player.getX()[0], player.getX()[1], player.getY()[0], player.getY()[1], WIDTH, LENGTH, poisonApple.getX(), poisonApple.getY())<0){
                gameEnd();
            }
            circle.setCenterX(apple.getX());
            circle.setCenterY(apple.getY());
        }
        if (isEaten(poisonApple)){
            gameEnd();
        }
    }

    private static boolean isEaten(Apples ga) {
        int ax1=player.getX()[0];
        int ax2=player.getX()[1];
        int ay1=player.getY()[0];
        int ay2=player.getY()[1];
        int bx1=ga.getExes()[0];
        int bx2=ga.getExes()[1];
        int by1=ga.getYes()[0];
        int by2=ga.getYes()[1];
        boolean y=( (bx1<ax2 && ax1<bx1)|| (ax1<bx2 && bx2<ax2));
        boolean x=( (by1<ay2 && ay1<by1)|| (ay1<by2 && by2<ay2) );
        return x && y;
    }

    private static class GameUnit extends Rectangle implements EventHandler<KeyEvent> {

        public GameUnit(double x, double y, double width, double height) {
            super(x, y, width, height);
            setFill(Color.BLACK);
        }

        private void checkInitBehavior() {
            if ( behavior == null ) {
                behavior = new GameUnitBehavior();
                new Thread(behavior).start();
            }
        }
        private GameUnitBehavior behavior;

        @Override
        public void handle(KeyEvent event) {
            checkInitBehavior();
            switch (event.getCode()) {
                case LEFT:
                    player.setX(-1, WIDTH);
                    behavior.moveToSide(Side.LEFT);
                    checkEat();
                    break;
                case RIGHT:
                    player.setX(1, WIDTH);
                    behavior.moveToSide(Side.RIGHT);
                    checkEat();
                    break;
                case UP:
                    player.setY(-1, LENGTH);
                    behavior.moveToSide(Side.TOP);
                    checkEat();
                    break;
                case DOWN:
                    player.setY(1, LENGTH);
                    behavior.moveToSide(Side.BOTTOM);
                    checkEat();
                    break;
            }
        }

        private class GameUnitBehavior extends Task {

            private boolean vertical = false;

            private boolean positiv = false;

            private double step = player.getVelocity();

            @Override
            protected Object call() throws Exception {
                while ( getScene().getWindow().isShowing() ) {
                    if ( vertical ) {
                        setX(player.get1());
                    } else {
                        setY(player.get2());
                    }
                    Thread.sleep(100);
                }
                return null;
            }

            public void moveToSide(Side side) {
                vertical = side.isVertical();
                positiv = side == (vertical ? Side.RIGHT : Side.BOTTOM );
            }
        }

    }
}
