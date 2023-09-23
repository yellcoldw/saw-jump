package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.awt.Button;

public enum Buttons {
    LEFT(0, Gdx.graphics.getWidth()/4, 0, 600, false),
    RIGHT(Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/2, 0, 600, false),
    JUMP(Gdx.graphics.getWidth()/2, Gdx.graphics.getWidth(), 0, 600, false);
    final int x1;
    final int x2;
    final int y1;
    final int y2;
    boolean isPressed;
    Buttons(int x1, int x2, int y1, int y2, boolean isPressed){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.isPressed = isPressed;
    }

}
