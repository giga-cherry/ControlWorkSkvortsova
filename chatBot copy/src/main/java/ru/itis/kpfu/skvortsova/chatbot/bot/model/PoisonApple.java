package ru.itis.kpfu.skvortsova.chatbot.bot.model;

import java.util.ArrayList;

public class PoisonApple implements Apples {
    private int x,y;
    private final int RADIUS =10;
    public PoisonApple(int sx1, int sx2, int sy1, int sy2, int width, int length) {
        generatePosition(sx1,sx2,sy1,sy2,width,length);
    }

    public int generatePosition(int sx1, int sx2, int sy1, int sy2, int width, int length){
        ArrayList<Integer> exes= new ArrayList<>();
        ArrayList<Integer> yes= new ArrayList<>();
        int a=RADIUS;
        while(a<width-RADIUS){
            if(a==sx1-RADIUS){
                a=sx2+RADIUS;
            }
            exes.add(a);
            a++;
        }
        a=RADIUS;
        while(a<length-RADIUS){
            if(a==sy1-RADIUS){
                a=sy2+RADIUS;
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
