package com.mygdx.game.game_play.cake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Cake extends Actor {

    Vector2 center;
    ShapeRenderer render;
    public final static float radius = 100;

    public Cake() {
        render = new ShapeRenderer();
        center = new Vector2(400, 400);
    }

    public double getInputAngel() {
        int x = Gdx.input.getX(), y = Gdx.input.getY();
        float delta_x = center.x - x, delta_y = y - center.y;
        if (Math.abs(delta_x) <= 1e-6) {
            if (Math.abs(delta_y) <= 1e-6) {
                return 0.0;
            } else if (delta_y > 0) {
                return Math.PI / 2.0f;
            } else {
                return Math.PI * 3.0f / 2.0f;
            }
        }
        return Math.atan2(delta_y, delta_x);
    }

    public boolean inputInsideCircle() {
        int x = Gdx.input.getX(), y = Gdx.input.getY();
        double delta_x = center.x - x, delta_y = center.y - y;
        double distance = delta_x * delta_x + delta_y * delta_y;
        return distance <= 2.25*radius*radius;
    }

    public void drawCircle() {
        render.begin(ShapeRenderer.ShapeType.Filled);
        render.setColor(1, 1, 1, 1);
        render.circle(center.x, center.y, radius);
        if (inputInsideCircle()) {
            render.setColor(1, 0, 0, 1);
            double angel = getInputAngel();
            for (double start=angel-0.05; start<=angel+0.05; start+=0.005) {
                float x1, y1, x2, y2;
                x1 = center.x - radius*(float)Math.cos(start)*0.98f;
                y1 = center.y - radius*(float)Math.sin(start)*0.98f;
                x2 = center.x - radius*(float)Math.cos(start+0.005)*0.98f;
                y2 = center.y - radius*(float)Math.sin(start+0.005)*0.98f;
                render.triangle(center.x, center.y, x1, y1, x2, y2);
            }
        }
        render.end();
    }

    @Override
    public void act(float delta) {
        boolean mousePressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        System.out.println(mousePressed);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawCircle();
    }
}
