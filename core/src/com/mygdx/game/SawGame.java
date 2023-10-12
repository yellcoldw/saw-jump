package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class SawGame extends Game {
	public SpriteBatch batch;
	public ShapeRenderer shape;
	public ArrayList<Vector2> inputs;
	public BitmapFont font;
	public Texture img;
	@Override
	public void create () {
		Constants.WIDTH.set(Gdx.graphics.getWidth());
		Constants.HEIGHT.set(Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		font = new BitmapFont();
		inputs = new ArrayList<>();
		img = new Texture(Gdx.files.internal("sprites/square.png"));
		for (int i = 0; i < 20; i++) {
			inputs.add(new Vector2(-1, -1));
		}
		Gdx.input.setInputProcessor(new GameInput(this));
		setScreen(new GameScreen(this));
	}
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		shape.dispose();

	}
}

