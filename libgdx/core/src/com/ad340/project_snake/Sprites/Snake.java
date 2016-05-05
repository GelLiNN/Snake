package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.ProjectSnake;
import com.ad340.project_snake.SwipeGestureDetector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

/**
 * Created by MasterOfTheUniverse on 4/21/16.
 */
public class Snake {

    // constants
    public static final float SNAKE_SPEED = 3f;
    public static final Vector2 STARTING_POS = new Vector2(200, 200);
    public static final Vector2 STARTING_VEL = new Vector2(0, SNAKE_SPEED);

    // state
    private World world;
    private List<SnakePiece> snakePieces;
    private Pair<Vector2, Vector2> ghost;
    Vector2 ghostLocation;
    Vector2 ghostVelocity;

    boolean isWaitingToAdd = false;

    public Snake(World world) {
        this.world = world;

        // setup the snake
        SnakePiece head = new SnakePiece(world, STARTING_POS, STARTING_VEL);
        snakePieces = new ArrayList<SnakePiece>();
        snakePieces.add(head);
        setupGestures();
    }

    public void addToSnake() {
        ghostLocation = new Vector2();
        ghostLocation.x = ghost.getKey().x;
        ghostLocation.y = ghost.getKey().y;
        ghostVelocity = new Vector2();
        ghostVelocity.x = ghost.getValue().x;
        ghostVelocity.y = ghost.getValue().y;
        float delay = 1; // seconds

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                // Do your work
                SnakePiece newTail = new SnakePiece(world, ghostLocation /* should be something else */,
                        ghostVelocity);
                snakePieces.add(newTail);

                System.out.println("waited delay :)");
            }
        }, delay);

        System.out.println("added to snake");
    }

    /**
     * Update all the snakepieces in this snake with the same dt
     * @param dt
     */
    public void update(float dt) {
        for (SnakePiece piece : snakePieces) {
            piece.update(dt);
        }

        updateGhost();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            addToSnake();
        }
    }

    private void updateGhost() {
        SnakePiece tail = snakePieces.get(snakePieces.size() - 1);
        Vector2 ghostPosition = tail.b2body.getWorldCenter();
        ghostPosition.x *= ProjectSnake.PPM;
        ghostPosition.y *= ProjectSnake.PPM;
        Vector2 ghostVelocity = tail.b2body.getLinearVelocity();
        this.ghost = new Pair<Vector2, Vector2>(ghostPosition, ghostVelocity);
    }

    public void draw(SpriteBatch batch) {
        for (SnakePiece piece : snakePieces) {
            piece.draw(batch);
        }
    }

    public void setupGestures() {
        // setup swipe gestures
        Gdx.input.setInputProcessor(new SwipeGestureDetector(new SwipeGestureDetector.DirectionListener() {

            @Override
            public void onUp() {
                SnakePiece head = snakePieces.get(0);

                Vector2 position = head.b2body.getWorldCenter();
                Vector2 velocity = new Vector2(0, SNAKE_SPEED);

                for (SnakePiece piece : snakePieces) {
                    piece.pivots.add(new Pair(position, velocity));
                }

                // testing
                System.out.println("swiped up");
            }

            @Override
            public void onRight() {
                SnakePiece head = snakePieces.get(0);

                Vector2 position = head.b2body.getWorldCenter();
                Vector2 velocity = new Vector2(SNAKE_SPEED, 0);

                for (SnakePiece piece : snakePieces) {
                    piece.pivots.add(new Pair(position, velocity));
                }

                // testing
                System.out.println("swiped right");
            }

            @Override
            public void onLeft() {
                SnakePiece head = snakePieces.get(0);

                Vector2 position = head.b2body.getWorldCenter();
                Vector2 velocity = new Vector2(-1 * SNAKE_SPEED, 0);

                for (SnakePiece piece : snakePieces) {
                    piece.pivots.add(new Pair(position, velocity));
                }

                // testing
                System.out.println("swiped left");
            }

            @Override
            public void onDown() {
                SnakePiece head = snakePieces.get(0);

                Vector2 position = head.b2body.getWorldCenter();
                Vector2 velocity = new Vector2(0, -1 * SNAKE_SPEED);

                for (SnakePiece piece : snakePieces) {
                    piece.pivots.add(new Pair(position, velocity));
                }

                // testing
                System.out.println("swiped down");
            }
        }));
    }
}
