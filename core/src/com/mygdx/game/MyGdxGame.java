package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.mygdx.game.game_play.GamePlayScreen;
import com.mygdx.game.game_play.cake.CakeStage;
import com.mygdx.game.main_menu.MainMenuScreen;
import com.mygdx.game.register.RegisterScreen;
import jdk.tools.jmod.Main;

public class MyGdxGame extends Game {

	private SpriteBatch batch;

	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(1440, 810);
		Gdx.graphics.setTitle("欧非大酬宾");
		batch = new SpriteBatch();
		this.setScreen(new GamePlayScreen(this));
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
