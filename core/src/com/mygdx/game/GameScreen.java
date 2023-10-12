package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen extends ScreenAdapter {
    SawGame game;
    GameLogic logic;

    public GameScreen(SawGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        logic = new GameLogic(game);
        Gdx.input.setInputProcessor(new GameInput(game));
        super.show();
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Buttons.update(game.inputs);
        if (!logic.player.getAlive()) {
            game.setScreen(new GameOverScreen(game));
        } else {
            logic.totalTime += delta;
            logic.timer -= delta;
            if (logic.timer <= 0) {
                logic.timer = 0;
                logic.timer2++;
                if (logic.timer2 % 50 == 0) {
                    logic.balls.createBall(1);
                }
            }
            if (logic.totalTime >= 2.5f) {
                logic.totalTime -= 2.5f;
                logic.balls.createBall(2);
            }
        }
        logic.update();
        logic.render();
    }
}
