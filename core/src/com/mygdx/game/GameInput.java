package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;

public class GameInput implements InputProcessor {
    SawGame game;

    public GameInput(SawGame game) {
        this.game = game;
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenY = game.HEIGHT - screenY;
        game.inputs.get(pointer).x = screenX;
        game.inputs.get(pointer).y = screenY;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        game.inputs.get(pointer).x = 0;
        game.inputs.get(pointer).y = 0;
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        game.inputs.get(pointer).x = 0;
        game.inputs.get(pointer).y = 0;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        screenY = game.HEIGHT - screenY;
        game.inputs.get(pointer).x = screenX;
        game.inputs.get(pointer).y = screenY;
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
