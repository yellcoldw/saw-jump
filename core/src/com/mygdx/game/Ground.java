package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ground {
    SawGame game;
    public Ground(SawGame game){
        this.game = game;
    }
    public void Draw(ShapeRenderer shape){
        shape.setColor(0.2f,0.2f,0.2f,1);
        shape.rect(0,0,game.WIDTH,game.GROUNDHEIGHT);
        shape.setColor(0f,0.5f,1f,1f);
        shape.triangle(0,300,game.WIDTH/4f-20,0,game.WIDTH/4f-20,game.GROUNDHEIGHT);
        shape.triangle(game.WIDTH/4f+20, 0, game.WIDTH/4f+20, game.GROUNDHEIGHT, game.WIDTH/2f-20, game.GROUNDHEIGHT/2f);
        shape.circle(game.WIDTH/4f*3+20, game.GROUNDHEIGHT/2f, game.GROUNDHEIGHT/4f);

    }
}
