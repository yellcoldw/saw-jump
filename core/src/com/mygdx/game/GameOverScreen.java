package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import sun.awt.X11.Screen;

public class GameOverScreen extends ScreenAdapter {
    SawGame game;
    public GameOverScreen(SawGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                for (int i = 0; i < 20; i++) {
                    game.inputs.set(i,new Vector2(-1, -1));
                }
                game.setScreen(new GameScreen(game));
                return true;
            }
        });
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.font.setColor(1,0,0,1);
        game.font.getData().setScale(10);
        game.font.draw(game.batch,"GAME OVER",0,Constants.HEIGHT.value/2f);
        game.batch.end();
       // super.render(delta);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
