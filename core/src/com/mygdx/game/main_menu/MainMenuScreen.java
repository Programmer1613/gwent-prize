package com.mygdx.game.main_menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.common.CommonScreen;

public class MainMenuScreen extends CommonScreen {

    Sprite bg;
    Stage stage;
    Cover[] covers;

    public MainMenuScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
        bg = new Sprite(new Texture("texture/cover/bg"));
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        addCover();
    }

    private void addCover() {
        covers = new Cover[] {
                new Cover("simple"),
                new Cover("guigu"),
                new Cover("dyson"),
                new Cover("isaac"),
                new Cover("undertale"),
                new Cover("katana"),
                new Cover("atelier"),
                new Cover("13_sentinels"),
                new Cover("ringfit"),
        };
        for (int i=0; i<covers.length; i++) {
            float x = (480 * (2 - i % 3)) + (480 - covers[i].getWidth())/ 2.0f;
            float y = 250 * (float)(i / 3) + 60;
            covers[i].setPosition(x, y);
            stage.addActor(covers[i]);
        }
    }

    @Override
    public void render(float delta) {
        batch.begin();
        bg.draw(batch);
        batch.end();

        stage.act();
        stage.draw();
    }
}
