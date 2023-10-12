package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;
import java.util.LinkedList;

public class GoldList {
    SawGame game;
    GameLogic logic;
    private LinkedList<Gold> golds;
    public GoldList (SawGame game, GameLogic logic) {
        this.game = game;
        this.logic = logic;
        golds = new LinkedList<>();
    }
    public int size() {
        return golds.size();
    }
    public Gold get(int i) {
        return golds.get(i);
    }
    public void checkCollision() {
        for (Iterator<Gold> it = golds.iterator(); it.hasNext(); ) {
            Gold gold = it.next();
            if (Math.abs(gold.position.x - logic.player.position.x) < Constants.GOLD_SIZE.value && Math.abs(logic.player.position.y - gold.position.y) < Constants.GOLD_SIZE.value) {
                gold.remove(it);
                logic.timer++;
            }
        }
    }
    public void update() {
        for (Iterator<Gold> it = golds.iterator(); it.hasNext(); ) {
            Gold gold = it.next();
            gold.update();
        }
        checkCollision();
    }
    public void draw(ShapeRenderer shape) {
        shape.setColor(1,1,0,1);
        for (Iterator<Gold> it = golds.iterator(); it.hasNext(); ) {
            Gold gold = it.next();
            shape.rect(gold.position.x, gold.position.y, Constants.GOLD_SIZE.value, Constants.GOLD_SIZE.value);
        }
    }
}
