package com.ad340.project_snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ad340.project_snake.Screens.PlayScreen;

public class ProjectSnake extends Game {

	// Game constants
	public static final int V_WIDTH = 1080;
	public static final int V_HEIGHT = 1920;

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}