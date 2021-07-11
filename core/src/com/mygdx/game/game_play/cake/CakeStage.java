package com.mygdx.game.game_play.cake;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class CakeStage extends Stage {
    MyGdxGame game;
    Viewport viewport;
    Cake cake;

    public CakeStage(MyGdxGame game) {
        this.game = game;
        viewport = new StretchViewport(2000, 2000);
        this.setViewport(viewport);
        this.cake = new Cake();
        this.addActor(cake);
    }


}
