package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GameLogic {
    SawGame game;
    public Player player;
    public GameUI gameUI;
    public GoldList golds;
    public BallList balls;
    public float totalTime = 0f;
    public int score = 0;
    public BitmapFont font;
    public float timer = 30;
    public int timer2 = 0;
    public GameLogic(SawGame game) {
        this.game = game;
        player = new Player(game.img, this);
        gameUI = new GameUI(game, this);
        golds = new GoldList(game, this);
        balls = new BallList(game, this);
        for (int i = 0; i < 20; i++) {
            game.inputs.set(i,new Vector2(-1, -1));
        }
    }


    public void update() {
        Buttons.update(game.inputs);
        balls.update();
        golds.update();
        player.update(Gdx.graphics.getDeltaTime());
    }
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.shape.begin(ShapeRenderer.ShapeType.Filled);
        player.draw(game.batch);
        gameUI.draw(game.shape);
        balls.draw(game.shape);
        golds.draw(game.shape);
        game.batch.end();
        game.shape.end();
    }
}
