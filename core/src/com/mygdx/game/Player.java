package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.bcel.internal.Const;

public class Player {
    public boolean isDead;
    public Vector2 position;
    public Sprite sprite;
    public float speed = 1500;
    public int jumpCount = 2;
    public float jumpCounter = .35f;
    public int jumpSpeed = 2500;
    public int fallSpeed = 800;
    public int baseFallSpeed = 800;
    public boolean prevJumpKey;
    public GameLogic logic;
    private boolean isAlive = true;
    public Vector2 prevPosition;
    public Player(Texture img,GameLogic logic) {
        this.logic = logic;
        sprite = new Sprite(img);
        position = new Vector2(Constants.WIDTH.value/2f,sprite.getHeight()/2);
        prevPosition = new Vector2(0,0);
    }
    public void setAlive(boolean b) {
        isAlive = b;
    }
    public boolean getAlive() {
        return isAlive;
    }
    public void update(float deltaTime){
        if (Buttons.LEFT.isPressed) {
            position.x -= deltaTime * speed;
            sprite.setScale(-1);
        }
        else if (Buttons.RIGHT.isPressed) {
            position.x += deltaTime * speed;
            sprite.setScale(1);
        }
        if (Buttons.JUMP.isPressed) {
            if (jumpCount > 0 && !prevJumpKey ) {
                jumpCount--;
                jumpCounter = 0.35f;
                fallSpeed = baseFallSpeed;
            }
            if (jumpCounter > 0) {
                jumpCounter -= deltaTime;
                position.y += deltaTime * jumpSpeed;
            }
        }
        else {
            if (position.y == Constants.GROUND_HEIGHT.value) {
                jumpCounter = 0.35f;
                jumpCount = 2;
            }
            else {
                jumpCounter = 0;
            }
        }
        position.y -= deltaTime * fallSpeed;
        position.x = Math.min(position.x,Constants.WIDTH.value - sprite.getWidth());
        position.x = Math.max(position.x,0);
        position.y = Math.max(Constants.GROUND_HEIGHT.value, position.y);
        position.y = Math.min(position.y, Constants.HEIGHT.value*5/6f);
        if (position.y != Constants.GROUND_HEIGHT.value) {
            fallSpeed += 10;
        }
        else {
            fallSpeed = baseFallSpeed;
        }
        if (prevPosition.y > position.y && position.y == Constants.GROUND_HEIGHT.value) {
            logic.balls.explode();
        }
        fallSpeed = Math.min(fallSpeed,1000);
        prevJumpKey = Buttons.JUMP.isPressed;
        prevPosition.x = position.x;
        prevPosition.y = position.y;
    }
    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

}
