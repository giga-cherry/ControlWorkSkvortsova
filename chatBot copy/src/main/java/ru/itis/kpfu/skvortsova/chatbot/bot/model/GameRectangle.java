package ru.itis.kpfu.skvortsova.chatbot.bot.model;

public class GameRectangle {

    private int x,y;
//    верхний левый угол
    private double velocity;
    private int size;

    public void setX(int a, int width){
        if (a<1){
            x= (int) (x-velocity);
            if (x<0){
                x=0;
            }
        }
        else{
            x= (int) (x+velocity);
            if (x+size>width){
                x=width-size;
            }
        }
    }

    public void setY(int a, int length){
        if (a<1){
            y= (int) (y-velocity);
            if (y<0){
                y=0;
            }
        }
        else{
            y= (int) (y+velocity);
            if (y+size>length){
                y=length-size;
            }
        }
    }
    public void eat(){
        size+=20;
        velocity++;
    }

    public int get1(){
        return x;
    }

    public int get2(){
        return y;
    }

    public int[] getX() {
        return new int[]{x, x+size};
    }

    public int[] getY() {
        return new int[]{y, y+size};
    }

    public double getVelocity() {
        return velocity;
    }

    public int getSize() {
        return size;
    }

    public GameRectangle() {
        this.size = 10;
        this.x=1;
        this.y=1;
        this.velocity=5.0;
    }
}
