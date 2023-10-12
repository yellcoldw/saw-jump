package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;

public class Gold {
    Vector2 position = new Vector2();
    Vector2 speed = new Vector2();
    boolean touchedGround = false;
    public Gold(float x, float y, float speedX, float speedY) {
        position.x = x;
        position.y = y;
        speed.x = speedX;
        speed.y = speedY;
    }
    public void remove(Iterator<Gold> it) {
        it.remove();
    }
    public void update() {
        if (touchedGround) {
            return;
            //position.y += 10*Math.sin()
        }
        if (position.x + speed.x + Constants.GOLD_SIZE.value > Constants.WIDTH.value + Constants.BALL_SIZE.value || position.x + speed.x < Constants.GOLD_SIZE.value) {
            speed.x = -speed.x;
        }
        position.x += speed.x;
        position.y += speed.y;
        if (speed.x > 0) {
            speed.x--;
        }
        if (speed.x < 0) {
            speed.x++;
        }
        speed.y--;
        position.x = Math.min(position.x,Constants.WIDTH.value - Constants.GOLD_SIZE.value);
        position.x = Math.max(position.x,0);
        position.y = Math.max(Constants.GROUND_HEIGHT.value, position.y);
        position.y = Math.min(position.y, Constants.HEIGHT.value*5/6f);
    }
}
