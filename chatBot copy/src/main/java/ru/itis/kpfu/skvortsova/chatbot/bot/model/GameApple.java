package ru.itis.kpfu.skvortsova.chatbot.bot.model;

import java.util.ArrayList;

public class GameApple implements Apples{

    private int x,y;
    private final int RADIUS =10;

    public int generatePosition(int sx1, int sx2, int sy1, int sy2, int width, int length, int q, int b){
        ArrayList<Integer> exes= new ArrayList<>();
        ArrayList<Integer> yes= new ArrayList<>();
        int a=RADIUS;
        while(a<width-RADIUS){
            if(a==sx1-RADIUS){
                a=sx2+RADIUS;
            }
            if (a==q-RADIUS){
                a=q+RADIUS+1;
            }
            exes.add(a);
            a++;
        }
        a=RADIUS;
        while(a<length-RADIUS){
            if(a==sy1-RADIUS){
                a=sy2+RADIUS;
            }
            if (a==b-RADIUS){
                a=b+RADIUS+1;
            }
            yes.add(a);
            a++;
        }
        if (exes.isEmpty() || yes.isEmpty()){
            return -1;
        }
        x= exes.get((int) (Math.random() * exes.size()));
        y= yes.get((int) (Math.random() * yes.size()));
        return 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GameApple(int sx1, int sx2, int sy1, int sy2, int width, int length, int a, int b) {
        generatePosition(sx1,sx2,sy1,sy2,width,length, a, b);
    }

    public int[] getExes(){
        return new int[]{x-RADIUS, x + RADIUS};
    }

    public int[] getYes(){
        return new int[]{y-RADIUS, y + RADIUS};
    }

    public int getRADIUS() {
        return RADIUS;
    }
}
