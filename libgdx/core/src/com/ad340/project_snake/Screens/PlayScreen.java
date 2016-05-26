package com.ad340.project_snake.Screens;

import com.ad340.project_snake.ProjectSnake;
import com.ad340.project_snake.Scenes.Hud;
import com.ad340.project_snake.Sprites.FoodController;
import com.ad340.project_snake.Sprites.Snake;
import com.ad340.project_snake.Utils.B2WorldCreator;
import com.ad340.project_snake.Utils.WorldContactListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Tim on 4/10/2016.
 * Created by Kellan on 4/10/2016.
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

    // Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    // Snake
    private Snake snake;

    // Food
    private FoodController foodControl;

    /**
     * PlayScreen constructor
     * @param game
     */
    public PlayScreen(ProjectSnake game) {
        this.game = game;

        // camera
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(ProjectSnake.V_WIDTH / ProjectSnake.PPM, ProjectSnake.V_HEIGHT / ProjectSnake.PPM, gameCam);

        // hud
        hud = new Hud(game.batch);

        // map
        maploader = new TmxMapLoader();
        map = maploader.load("Level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/ ProjectSnake.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        // box2d world for physics simulations
        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);

        // define the snake and add it to the world
        snake = new Snake(world);

        // define the food controller and add it to the world
        foodControl = new FoodController(world, map);

        // setup world contact listener
        world.setContactListener(new WorldContactListener());
    }

    /**
     * This will handle losing the game and changing screens.
     */
    public void loseGame() {
        // TODO: something
        System.out.println("You lose!");
    }

    /**
     * Updates the game every dt amount of time
     * @param dt time between updates
     */
    public void update(float dt) {
        world.step(1 / 60f, 6, 2);
        snake.update(dt);
        foodControl.update(dt);
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        // seperate our update logic from render
        update(delta);

        // clear game screen with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render our game map
        renderer.render();

        // gives us box2d debug lines
        b2dr.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        snake.draw(game.batch);
        foodControl.draw(game.batch);
        game.batch.end();

        // set our batch to draw what the Hud camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    /**
     * Disposes of everything
     */
    @Override
    public void dispose() {
        world.dispose();
        map.dispose();
        renderer.dispose();
        b2dr.dispose();
        hud.dispose();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void show () {}

    @Override
    public void hide() {}
}