package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

import java.util.HashMap;
import java.util.Vector;

public class Player {
    public Vector2 position;
    public Sprite sprite;
    public float speed = 1500;
    public int jumpCount = 2;
    public float jumpCounter = .35f;
    public int jumpSpeed = 2500;
    public int fallSpeed = 800;
    public int baseFallSpeed = 800;
    public int ground = 600;
    public boolean prevJumpKey;
    public SawGame game;
    public Vector2 prevPosition;
    public Player(Texture img,SawGame game) {
        this.game = game;
        sprite = new Sprite(img);
        position = new Vector2(Gdx.graphics.getWidth()/2f,sprite.getScaleY()*sprite.getHeight()/2);
        prevPosition = new Vector2(0,0);
    }
    public void Update(float deltaTime){
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
            if (position.y == ground) {
                jumpCounter = 0.35f;
                jumpCount = 2;
            }
            else {
                jumpCounter = 0;
            }
        }
        position.y -= deltaTime * fallSpeed;
        position.x = Math.min(position.x,Gdx.graphics.getWidth()-sprite.getWidth());
        position.x = Math.max(position.x,0);
        position.y = Math.max(ground,position.y);
        position.y = Math.min(position.y, Gdx.graphics.getHeight()*5/6f);
        if (position.y != ground) {
            fallSpeed += 10;
        }
        else {
            fallSpeed = baseFallSpeed;
        }
        if ( prevPosition.y > position.y && position.y == ground) {
            game.explode();
        }
        fallSpeed = Math.min(fallSpeed,1000);
        prevJumpKey = Buttons.JUMP.isPressed;
        prevPosition.x = position.x;
        prevPosition.y = position.y;
    }
    public void Draw(SpriteBatch batch) {
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

}
