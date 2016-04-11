package com.ad340.project_snake.Screens;

import com.ad340.project_snake.ProjectSnake;
import com.ad340.project_snake.Scenes.Hud;
import com.ad340.project_snake.SwipeGestureDetector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Tim on 4/10/2016.
 */
public class PlayScreen implements Screen {

    private ProjectSnake game;

    // camera
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    // hud
    private Hud hud;

    // map
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    public PlayScreen(ProjectSnake game) {
        this.game = game;

        // camera
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(ProjectSnake.V_WIDTH, ProjectSnake.V_HEIGHT, gameCam);

        // hud
        hud = new Hud(game.batch);

        // setup gestures
        Gdx.input.setInputProcessor(new SwipeGestureDetector(new SwipeGestureDetector.DirectionListener() {

            @Override
            public void onUp() {
                //manipulate the snake tile in the map
                System.out.println("swiped up");
                // change direction of the head of snake to moving upwards, unless it's moving down
            }

            @Override
            public void onRight() {
                //manipulate the snake tile in the map
                System.out.println("swiped right");
                // change direction of the head of snake to moving right, unless it's moving left
            }

            @Override
            public void onLeft() {
                //manipulate the snake tile in the map
                System.out.println("swiped left");
                // change direction of the head of snake to moving left, unless it's moving right
            }

            @Override
            public void onDown() {
                //manipulate the snake tile in the map
                System.out.println("swiped down");
                // change direction of the head of snake to moving down, unless it's moving up
            }
        }));

        // map
        maploader = new TmxMapLoader();
        map = maploader.load("snakeTestMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        TiledMapTile snake;
    }

    public void update(float dt) {
        handleInput(dt);
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

    @Override
    public void dispose() {

    }
}