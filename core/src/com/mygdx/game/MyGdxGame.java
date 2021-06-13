package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.register.RegisterScreen;

public class MyGdxGame extends Game {

	private SpriteBatch batch;

	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(1440, 810);
		batch = new SpriteBatch();
		this.setScreen(new RegisterScreen(this));
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
