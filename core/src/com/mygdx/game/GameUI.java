package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameUI {
    GameLogic logic;
    SawGame game;
    public GameUI(SawGame game, GameLogic logic){
        this.logic = logic;
        this.game = game;
    }
    public void draw(ShapeRenderer shape){
        shape.setColor(0.2f,0.2f,0.2f,1);
        shape.rect(0,0,Constants.WIDTH.value, Constants.GROUND_HEIGHT.value);
        //LEFT BUTTON
        shape.setColor(0f,0.5f,1f,1f);
        if (Buttons.LEFT.isPressed) {
            shape.setColor(0.5f, 0, 0, 1);
        }
        shape.triangle(0,300,Constants.WIDTH.value/4f-20,0,Constants.WIDTH.value/4f-20,Constants.GROUND_HEIGHT.value);
        //RIGHT BUTTON
        shape.setColor(0, 0.5f, 1f, 1f);
        if (Buttons.RIGHT.isPressed) {
            shape.setColor(0.5f, 0, 0, 1);
        }
        shape.triangle(Constants.WIDTH.value/4f+20, 0, Constants.WIDTH.value/4f+20, Constants.GROUND_HEIGHT.value, Constants.WIDTH.value/2f-20, Constants.GROUND_HEIGHT.value/2f);
        //JUMP BUTTON
        shape.setColor(0, 0.5f, 1f, 1f);
        if (Buttons.JUMP.isPressed) {
            shape.setColor(0.5f, 0, 0,1);
        }
        shape.circle(Constants.WIDTH.value/4f*3+20, Constants.GROUND_HEIGHT.value/2f, Constants.GROUND_HEIGHT.value/4f);
        //SCORE AND TIME
        game.font.setColor(1,0,1,1);
        game.font.getData().setScale(10);
        game.font.draw(game.batch,Integer.toString(logic.score),0,Constants.HEIGHT.value-50);
        game.font.draw(game.batch,Integer.toString((int)logic.timer),Constants.WIDTH.value-300,Constants.HEIGHT.value-50);
    }
}
