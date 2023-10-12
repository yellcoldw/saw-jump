package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.awt.Button;
import java.util.ArrayList;
import java.util.Vector;

public enum Buttons {
    LEFT(0, Gdx.graphics.getWidth()/4, 0, 600, false),
    RIGHT(Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/2, 0, 600, false),
    JUMP(Gdx.graphics.getWidth()/2, Gdx.graphics.getWidth(), 0, 600, false);
    final int x1;
    final int x2;
    final int y1;
    final int y2;
    boolean isPressed;
    public static boolean checkRectangle(int x1, int y1, int x2, int y2, float x3, float y3) {
        if (x3 >= x1 && x3 <= x2 && y3 >= y1 && y3 <= y2) {
            return true;
        }
        return false;
    }
    public static void update(ArrayList<Vector2> inputs) {
        for (Buttons button : Buttons.values()) {
            button.isPressed = false;
        }
        for (Buttons button : Buttons.values()) {
            for (int i = 0; i < 20; i++) {
                if (button.isPressed = checkRectangle(button.x1, button.y1, button.x2, button.y2, inputs.get(i).x, inputs.get(i).y)) {
                    break;
                }
            }
        }
    }
    Buttons(int x1, int x2, int y1, int y2, boolean isPressed){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.isPressed = isPressed;
    }

}
