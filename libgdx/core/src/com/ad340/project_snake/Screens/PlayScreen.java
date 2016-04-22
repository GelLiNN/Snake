package com.ad340.project_snake.Screens;

import com.ad340.project_snake.ProjectSnake;
import com.ad340.project_snake.Scenes.Hud;
import com.ad340.project_snake.Sprites.Snake;
import com.ad340.project_snake.SwipeGestureDetector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
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

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    //Snake
    private Snake snake;
    public static final float SNAKE_SPEED = 100f;

    public PlayScreen(ProjectSnake game) {
        this.game = game;

        // camera
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(ProjectSnake.V_WIDTH, ProjectSnake.V_HEIGHT, gameCam);

        // hud
        hud = new Hud(game.batch);

        // map
        maploader = new TmxMapLoader();
        map = maploader.load("Level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        // box2d world for physics simulations
        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // define the snake and add to the world
        snake = new Snake(this.world);

        // get boundary fixtures from layer 1 "boundaries" layer
        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // get food fixtures from layer 2 "food" layer
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // setup gestures
        Gdx.input.setInputProcessor(new SwipeGestureDetector(new SwipeGestureDetector.DirectionListener() {

            @Override
            public void onUp() {
                //manipulate the snake tile in the map
                System.out.println("swiped up");
                snake.b2body.setLinearVelocity(0, 0);
                snake.b2body.applyLinearImpulse(new Vector2(0, SNAKE_SPEED), snake.b2body.getWorldCenter(), true);
                // todo: change direction of the head of snake to moving upwards, unless it's moving down
            }

            @Override
            public void onRight() {
                //manipulate the snake tile in the map
                System.out.println("swiped right");
                snake.b2body.setLinearVelocity(0, 0);
                snake.b2body.applyLinearImpulse(new Vector2(SNAKE_SPEED, 0), snake.b2body.getWorldCenter(), true);
                // change direction of the head of snake to moving right, unless it's moving left
            }

            @Override
            public void onLeft() {
                //manipulate the snake tile in the map
                System.out.println("swiped left");
                snake.b2body.setLinearVelocity(0, 0);
                snake.b2body.applyLinearImpulse(new Vector2(-1 * SNAKE_SPEED, 0), snake.b2body.getWorldCenter(), true);
                // change direction of the head of snake to moving left, unless it's moving right
            }

            @Override
            public void onDown() {
                //manipulate the snake tile in the map
                System.out.println("swiped down");
                snake.b2body.setLinearVelocity(0, 0);
                snake.b2body.applyLinearImpulse(new Vector2(0, -1 * SNAKE_SPEED), snake.b2body.getWorldCenter(), true);
                // change direction of the head of snake to moving down, unless it's moving up
            }
        }));
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        TiledMapTile snake;
    }

    public void update(float dt) {
        //handle user input first
        handleInput(dt);

        world.step(1/60f, 6, 2);

        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        //gives us box2d debug lines
        b2dr.render(world, gameCam.combined);

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