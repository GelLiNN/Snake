package com.ad340.project_snake;

import com.ad340.project_snake.Screens.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ad340.project_snake.Screens.PlayScreen;

/**
 * Created by Kellan on 4/10/2016.
 */
public class ProjectSnake extends Game {

	// Game constants
	public static final int V_WIDTH = 1080;
	public static final int V_HEIGHT = 1920;
	public static final float PPM = 100;
	// Bit constants
	public static final short DEFAULT_BIT = 1;
	public static final short SNAKE_BIT = 2;
	public static final short BOUNDARY_BIT = 4;
	public static final short FOOD_BIT = 8;
	public static final short DESTROYED_BIT = 16;

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}