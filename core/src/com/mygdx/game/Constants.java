package com.mygdx.game;

public enum Constants {
    BALL_SIZE(50),
    GROUND_HEIGHT(600),
    GOLD_SIZE(40),
    WIDTH(1080),
    HEIGHT(2400);
    int value;
    Constants (int value) {
        this.value = value;
    }
    public void set(int val) {
        value = val;
    }

}
