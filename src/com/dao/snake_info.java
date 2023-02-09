package com.dao;

public class snake_info {
    public int x,y;

    public snake_info(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }


    public void setx(int x){
        this.x = x;
    }

    public void sety(int y){
        this.y = y;
    }
}
