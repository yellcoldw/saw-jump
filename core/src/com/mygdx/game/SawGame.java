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
import java.util.Vector;


public class SawGame extends Game {
	public final int GOLDSIZE = 40;
	public final int BALLSIZE = 50;
	public final int GROUNDHEIGHT = 600;
	public SpriteBatch batch;
	public int WIDTH;
	public int HEIGHT;
	public Texture img;
	public Player player;
	public Ground ground;
	public ArrayList<Vector2> inputs;
	public ShapeRenderer shape;
	public ArrayList<Vector2> balls;
	public ArrayList<Vector2> ballsSpeed;
	public ArrayList<Boolean> mark;
	public float totalTime = 0f;
	public int score = 0;
	public BitmapFont font;
	public boolean isDead = false;
	public float timer = 30;
	public ArrayList<Vector2> gold;
	public int timer2 = 0;
	@Override
	public void create () {
		font = new BitmapFont();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("sprites/square.png"));
		player = new Player(img,this);
		inputs = new ArrayList<>();
		balls = new ArrayList<>();
		ballsSpeed = new ArrayList<>();
		gold = new ArrayList<>();
		mark = new ArrayList<>();
		ground = new Ground(this);
		for (int i = 0; i < 20; i++) {
			inputs.add(new Vector2(0, 0));
		}
		for (int i = 0; i < 500; i++) {
			mark.add(false);
		}
		Gdx.input.setInputProcessor(new GameInput(this));
		shape = new ShapeRenderer();
	}
	public void checkButtons(){
		Buttons.LEFT.isPressed = false;
		Buttons.RIGHT.isPressed = false;
		Buttons.JUMP.isPressed = false;
		for (int i = 0; i<20; i++){
			if (inputs.get(i).x >= Buttons.LEFT.x1 && inputs.get(i).x <= Buttons.LEFT.x2 && inputs.get(i).y >= Buttons.LEFT.y1 && inputs.get(i).y <= Buttons.LEFT.y2 && (inputs.get(i).x !=0 || inputs.get(i).y !=0) ) {
				Buttons.LEFT.isPressed = true;
			}
		}
		for (int i = 0; i<20; i++){
			if (inputs.get(i).x >= Buttons.RIGHT.x1 && inputs.get(i).x <= Buttons.RIGHT.x2 && inputs.get(i).y >= Buttons.RIGHT.y1 && inputs.get(i).y <= Buttons.RIGHT.y2 && (inputs.get(i).x !=0 || inputs.get(i).y !=0)) {
				Buttons.RIGHT.isPressed = true;
			}
		}
		for (int i = 0; i<20; i++){
			if (inputs.get(i).x >= Buttons.JUMP.x1 && inputs.get(i).x <= Buttons.JUMP.x2 && inputs.get(i).y >= Buttons.JUMP.y1 && inputs.get(i).y <= Buttons.JUMP.y2 && (inputs.get(i).x !=0 || inputs.get(i).y !=0)) {
				Buttons.JUMP.isPressed = true;
			}
		}
	}
	public void addMarks() {
		for (int i = 0; i < balls.size(); i++) {
			if (Math.abs((player.position.x+64)  - balls.get(i).x) < 30 && player.position.y > balls.get(i).y) {
				mark.set(i,true);
			}
		}
	}
	public void explode() {
		for (int i = 0; i < balls.size(); i++) {
			if (mark.get(i)) {
				gold.add(new Vector2(balls.get(i).x, balls.get(i).y));
				balls.get(i).x = -100;
				balls.get(i).y = -100;
				ballsSpeed.get(i).x = 0;
				ballsSpeed.get(i).y = 0;
				score++;
				mark.set(i, false);
			}
		}
	}
	public void updateBalls() {
		for (int i = 0; i < balls.size(); i++) {
			if (balls.get(i).y + ballsSpeed.get(i).y > HEIGHT-BALLSIZE  && ballsSpeed.get(i).y > 0 ) {
				balls.get(i).x = -100;
				balls.get(i).y = -100;
				ballsSpeed.get(i).x = 0;
				ballsSpeed.get(i).y = 0;
			}
			if (balls.get(i).x + ballsSpeed.get(i).x > WIDTH-BALLSIZE || balls.get(i).x + ballsSpeed.get(i).x < BALLSIZE) {
				ballsSpeed.get(i).x *= -1;
			}
			if (balls.get(i).y + ballsSpeed.get(i).y > HEIGHT-BALLSIZE || balls.get(i).y + ballsSpeed.get(i).y < BALLSIZE+GROUNDHEIGHT) {
				ballsSpeed.get(i).y *= -1;
			}
			balls.get(i).x += ballsSpeed.get(i).x;
			balls.get(i).y += ballsSpeed.get(i).y;
		}
	}
	public void checkCollision() {
		for (int i = 0; i < balls.size(); i++) {
			if (Math.abs((balls.get(i).x) - (player.position.x+64))<80 && Math.abs((balls.get(i).y) - (player.position.y+64)) <80) {
				isDead = true;
			}
		}
	}
	public void checkGold() {
		for (int i = 0; i < gold.size(); i++) {
			if (Math.abs(gold.get(i).x - player.position.x) < GOLDSIZE && Math.abs(player.position.y-gold.get(i).y) < GOLDSIZE) {
				timer +=1;
				gold.set(i,new Vector2(10000,10000));
			}
			else {
				gold.get(i).y = Math.max(gold.get(i).y -Gdx.graphics.getDeltaTime() * player.fallSpeed, player.ground);

			}
		}
	}
	public void createBall(int num) {
		for (int i = 0; i < num; i++) {
			balls.add(new Vector2((float)Math.random()*(WIDTH-200) + 100 ,HEIGHT-BALLSIZE));
			float randomSpeed = ((float) Math.random() * 6) +2;
			float randomSpeed2 = -1f * (float)Math.sqrt(100f - randomSpeed * randomSpeed);
			ballsSpeed.add(new Vector2(randomSpeed, randomSpeed2));
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (isDead) {
			renderGameOver();
		} else {
			updateGameLogic();
			renderGame();
		}
	}

	private void renderGameOver() {
		batch.begin();
		font.setColor(1, 0, 0, 1);
		font.getData().setScale(10);
		font.draw(batch, "GAME OVER", 0, HEIGHT / 2f);
		batch.end();
	}

	private void updateGameLogic() {
		totalTime += Gdx.graphics.getDeltaTime();
		timer -= Gdx.graphics.getDeltaTime();

		if (timer <= 0) {
			timer = 0;
			timer2++;
			if (timer2 % 50 == 0) {
				createBall(1);
			}
		}

		updateBalls();
		addMarks();

		if (totalTime >= 2.5f) {
			totalTime -= 2.5f;
			createBall(2);
		}

		checkButtons();
	}

	private void renderGame() {
		batch.begin();
		font.setColor(1, 0, 1, 1);
		font.getData().setScale(10);
		font.draw(batch, Integer.toString(score), 0, HEIGHT - 50);
		font.draw(batch, Integer.toString((int) timer), WIDTH - 300, HEIGHT - 50);
		player.Draw(batch);
		batch.end();

		shape.begin(ShapeRenderer.ShapeType.Filled);
		ground.Draw(shape);
		shape.setColor(1, 1, 1, 1);

		for (int i = 0; i < balls.size(); i++) {
			if (!mark.get(i)) {
				shape.circle(balls.get(i).x, balls.get(i).y, 50);
			}
		}

		shape.setColor(0, 1, 0, 1);

		for (int i = 0; i < balls.size(); i++) {
			if (mark.get(i)) {
				shape.circle(balls.get(i).x, balls.get(i).y, 50);
			}
		}

		shape.setColor(1, 1, 0, 1);

		for (int i = 0; i < gold.size(); i++) {
			shape.rect(gold.get(i).x, gold.get(i).y, 40, 40);
		}

		shape.end();
	}


	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		shape.dispose();

	}
}
