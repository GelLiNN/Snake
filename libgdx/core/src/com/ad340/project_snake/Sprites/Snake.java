package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.ProjectSnake;
import com.ad340.project_snake.Screens.PlayScreen;
import com.ad340.project_snake.SwipeGestureDetector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.List;

/**
 * Created by MasterOfTheUniverse on 4/21/16.
 */
public class Snake extends Sprite {
    // constants
    public static final float SNAKE_SPEED = 200f;
    public static final int SNAKE_FIXTURE_RADIUS = 10;

    // other snake data
    public World world;
    public Body b2body;
    public List<Snake> children;

    public Snake(World world, PlayScreen screen) {
        super(new Texture("snake-sprite.png"));
        this.world = world;
        defineSnake();
        setBounds(0, 0, 32 / ProjectSnake.PPM, 32 / ProjectSnake.PPM);
        setupGestures();
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void defineSnake() {
        // create a body definition for box2d
        BodyDef bdef = new BodyDef();
        bdef.position.set(200, 200);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        // create a fixture definition for box2d
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(SNAKE_FIXTURE_RADIUS);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public void setupGestures() {
        // setup gestures
        Gdx.input.setInputProcessor(new SwipeGestureDetector(new SwipeGestureDetector.DirectionListener() {

            @Override
            public void onUp() {
                //manipulate the snake tile in the map
                System.out.println("swiped up");
                b2body.setLinearVelocity(0, 0);
                b2body.applyLinearImpulse(new Vector2(0, SNAKE_SPEED), b2body.getWorldCenter(), true);
                // todo: change direction of the head of snake to moving upwards, unless it's moving down
            }

            @Override
            public void onRight() {
                //manipulate the snake tile in the map
                System.out.println("swiped right");
                b2body.setLinearVelocity(0, 0);
                b2body.applyLinearImpulse(new Vector2(SNAKE_SPEED, 0), b2body.getWorldCenter(), true);
                // change direction of the head of snake to moving right, unless it's moving left
            }

            @Override
            public void onLeft() {
                //manipulate the snake tile in the map
                System.out.println("swiped left");
                b2body.setLinearVelocity(0, 0);
                b2body.applyLinearImpulse(new Vector2(-1 * SNAKE_SPEED, 0), b2body.getWorldCenter(), true);
                // change direction of the head of snake to moving left, unless it's moving right
            }

            @Override
            public void onDown() {
                //manipulate the snake tile in the map
                System.out.println("swiped down");
                b2body.setLinearVelocity(0, 0);
                b2body.applyLinearImpulse(new Vector2(0, -1 * SNAKE_SPEED), b2body.getWorldCenter(), true);
                // change direction of the head of snake to moving down, unless it's moving up
            }
        }));
    }
}
