package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sun.org.apache.bcel.internal.Const;

import java.util.Iterator;
import java.util.LinkedList;

public class BallList {
    SawGame game;
    GameLogic logic;
    private LinkedList<Ball> balls;
    public BallList(SawGame game, GameLogic logic) {
        this.game = game;
        this.logic = logic;
        balls = new LinkedList<>();
    }
    public int size() {
        return balls.size();
    }
    public Ball get(int i) {
        return balls.get(i);
    }

    public void checkCollision() {
        for (Iterator<Ball> it = balls.iterator(); it.hasNext(); ) {
            Ball ball = it.next();
            if (Math.abs(ball.position.x - (logic.player.position.x + 64)) < 80 && Math.abs(ball.position.y - (logic.player.position.y + 64)) < 80 ) {
                logic.player.setAlive(false);
            }
        }
    }

    public void createBall(int n) {;
        for (int i = 0; i < n; i++) {
            float randomSpeed = ((float) Math.random() * 4) +3;
            float randomSpeedY = -1f * (float)Math.sqrt(100f - randomSpeed * randomSpeed);
            balls.add(new Ball((float)Math.random()*(Constants.WIDTH.value-200) + 100, Constants.HEIGHT.value - Constants.BALL_SIZE.value, randomSpeed, randomSpeedY));
        }
    }
    public void explode() {
        for (Iterator<Ball> it = balls.iterator(); it.hasNext(); ) {
            Ball ball = it.next();
            if (ball.getMark()) {
                ball.explode(it);
            }
        }
    }
    public void update() {
        for (Iterator<Ball> it = balls.iterator(); it.hasNext(); ) {
            Ball ball = it.next();
            ball.update(it);
            ball.updateMark(logic.player.position.x, logic.player.position.y);
        }
        checkCollision();
    }
    public void draw(ShapeRenderer shape) {
        for (Iterator<Ball> it = balls.iterator(); it.hasNext(); ) {
            Ball ball = it.next();
            if (ball.getMark()) {
                shape.setColor(0,1,0,1);
            }
            else {
                shape.setColor(1,1,1,1);
            }
            shape.circle(ball.position.x, ball.position.y, Constants.BALL_SIZE.value);
        }
    }
}
