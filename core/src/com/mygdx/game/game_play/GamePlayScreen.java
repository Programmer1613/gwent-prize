package com.mygdx.game.game_play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.common.CommonScreen;
import com.mygdx.game.game_play.cake.CakeStage;

public class GamePlayScreen extends CommonScreen {
    Stage gameStage;
    ShapeRenderer shapeRenderer;
    public GamePlayScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
        gameStage = new CakeStage(game);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 1, 1);
        shapeRenderer.rect(
                0,
                Gdx.graphics.getHeight() * 0.9f,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight() * 0.1f
        );
        shapeRenderer.rect(
                Gdx.graphics.getWidth() * 0.9f,
                0,
                Gdx.graphics.getWidth() * 0.1f,
                Gdx.graphics.getHeight()
        );
        shapeRenderer.end();
        gameStage.act();
        gameStage.draw();
    }
}
