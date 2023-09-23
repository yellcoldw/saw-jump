package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import sun.awt.X11.Screen;

public class GameOverScreen extends ScreenAdapter {
    SawGame game;
    BitmapFont font;
    public GameOverScreen(SawGame game) {
        this.game = game;
        font = new BitmapFont();
    }

    @Override
    public void show() {
        super.show();
    }
    @Override
    public void render(float delta) {
        game.batch.begin();
        font.getData().setScale(15);
        font.draw(game.batch, "GAME OVER",Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f);
        game.batch.end();
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
