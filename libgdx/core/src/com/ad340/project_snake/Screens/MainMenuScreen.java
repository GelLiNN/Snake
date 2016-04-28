package com.ad340.project_snake.Screens;

import com.ad340.project_snake.ProjectSnake;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by austin on 4/26/16.
 */
public class MainMenuScreen implements Screen {
    private ProjectSnake game;

    private OrthographicCamera menuCam;
    private BitmapFont font;

    public MainMenuScreen(ProjectSnake game) {
        this.game = game;

        menuCam = new OrthographicCamera();
        menuCam.setToOrtho(false, 1080, 1920);

        font = new BitmapFont();
        font.setColor(Color.WHITE);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        menuCam.update();
        game.batch.setProjectionMatrix(menuCam.combined);

        game.batch.begin();
        font.draw(game.batch, "Welcome to Snake!!! ", 100, 150);
        font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new PlayScreen(game));
            dispose();
        }
    }

    @Override
    public void dispose () {

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}