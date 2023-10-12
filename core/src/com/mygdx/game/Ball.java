package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;

public class Ball {
    Vector2 position = new Vector2();
    Vector2 speed = new Vector2();
    private boolean isMarked = false;

    public void updateMark(float playerX, float playerY) {
            if (Math.abs(playerX + 64 - position.x) < 25 && playerY > position.y) {
                isMarked = true;
            }
    }
    public boolean getMark() {
        return isMarked;
    }

    public Ball(float x, float y, float speedX, float speedY) {
        position.x = x;
        position.y = y;
        speed.x = speedX;
        speed.y = speedY;
    }
    public void addMark() {
        isMarked = true;
    }
    public void explode(Iterator<Ball> it) {
        it.remove();
    }
    public void update(Iterator<Ball> it) {
        if (position.y + speed.y > Constants.HEIGHT.value - Constants.BALL_SIZE.value && speed.y > 0) {
            it.remove();
            return;
        }
        if (position.x + speed.x > Constants.WIDTH.value - Constants.BALL_SIZE.value || position.x + speed.x < Constants.BALL_SIZE.value) {
            speed.x = -speed.x;
        }
        if (position.y + speed.y > Constants.HEIGHT.value - Constants.BALL_SIZE.value || position.y + speed.y < Constants.BALL_SIZE.value + Constants.GROUND_HEIGHT.value) {
            speed.y = -speed.y;
        }
        position.x += speed.x;
        position.y += speed.y;
    }
}
