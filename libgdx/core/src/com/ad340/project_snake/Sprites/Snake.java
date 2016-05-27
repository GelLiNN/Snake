package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.ProjectSnake;
import com.ad340.project_snake.Utils.SwipeGestureDetector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ArrayMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by MasterOfTheUniverse on 4/21/16.
 */
public class Snake {
    public enum Swipe { UP, RIGHT, DOWN, LEFT };

    // constants
    public static final float SNAKE_SPEED = 3f;
    public static final Vector2 STARTING_POS = new Vector2(200, 200); // x, y
    public static final Vector2 STARTING_VEL = new Vector2(0, SNAKE_SPEED); // x-vel, y-vel
    public static final boolean isHead = true;

    // snake state
    private World world;
    private SnakePiece head; // points to the head
    private SnakePiece tail; // points to the tail
    private List<SnakePiece> snakePieces;
    private int snakeSize;

    // add data
    Vector2 addLocation;
    Vector2 addVelocity;

    /**
     * Snake constructor
     * @param world to place the Snake in
     */
    public Snake(World world) {
        this.world = world;

        // setup the snake
        head = new SnakePiece(world, STARTING_POS, STARTING_VEL, isHead);
        tail = head;
        snakePieces = new ArrayList<SnakePiece>();
        snakePieces.add(head);
        snakeSize = 1;

        // setup swipe gestures
        setupGestures();
    }

    /**
     * Adds a SnakePiece to the end of the Snake
     */
    public void addToSnake() {
        // update location and velocity for new piece that's being spawned
        updateAddData();

        Vector2 tailVel = tail.b2body.getLinearVelocity();
        if (tailVel.x > 0) {
            addLocation.x -= 35;
        } else if (tailVel.x < 0) {
            addLocation.x += 35;
        } else if (tailVel.y > 0) {
            addLocation.y -= 35;
        } else if (tailVel.y < 0) {
            addLocation.y += 35;
        }

        // create a new SnakePiece
        SnakePiece newTail = new SnakePiece(world, addLocation, addVelocity, !isHead);

        // Give it the tail's current pivots
        newTail.pivots = new LinkedList<ArrayMap<Vector2, Vector2>>(tail.pivots);

        // Add it to the list of SnakePieces
        snakePieces.add(newTail);

        // Set it as the new Tail
        tail = newTail;

        // Update snake size
        snakeSize++;

        System.out.println("Added to snake");
    }

    /**
     * Update location and velocity for new piece that's being spawned
     */
    public void updateAddData() {
        float x = tail.b2body.getWorldCenter().x * ProjectSnake.PPM,
              y = tail.b2body.getWorldCenter().y * ProjectSnake.PPM;
        addLocation = new Vector2(x, y);
        addVelocity = new Vector2(tail.b2body.getLinearVelocity());
    }

    public int getSize() {
        return snakeSize;
    }

    public List<SnakePiece> getSnakePieces() {
        return snakePieces;
    }

    /**
     * Update all the snakepieces in this snake with the same dt
     * @param dt
     */
    public void update(float dt) {
        for (SnakePiece piece : snakePieces) {
            piece.update(dt);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            addToSnake();
        }
    }

    /**
     * Draws the snake pieces every frame
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        for (SnakePiece piece : snakePieces) {
            piece.draw(batch);
        }
    }

    /**
     * Sets up swipe guestures for changing snake directions
     */
    public void setupGestures() {

        // setup swipe gestures
        Gdx.input.setInputProcessor(new SwipeGestureDetector(new SwipeGestureDetector.DirectionListener() {

            @Override
            public void onUp() {
                turnSnake(Swipe.UP);
            }

            @Override
            public void onRight() {
                turnSnake(Swipe.RIGHT);
            }

            @Override
            public void onDown() {
                turnSnake(Swipe.DOWN);
            }

            @Override
            public void onLeft() {
                turnSnake(Swipe.LEFT);
            }

            public void turnSnake(Swipe direction) {
                Vector2 headVel = head.b2body.getLinearVelocity();

                // get the pivot position
                float x = head.b2body.getWorldCenter().x * ProjectSnake.PPM,
                        y = head.b2body.getWorldCenter().y * ProjectSnake.PPM;
                Vector2 pivotPosition = new Vector2(x, y);

                // turn up or down
                if (direction == Swipe.UP || direction == Swipe.DOWN) {
                    // make sure the snake isn't moving up or down
                    if  (headVel.y == 0) {
                        // adjust the pivot position for next frame
                        if (headVel.x > 0) {
                            pivotPosition.x += 5;
                        } else {
                            pivotPosition.x -= 5;
                        }

                        // get the pivot velocity
                        Vector2 pivotVelocity;
                        if (direction == Swipe.UP) {
                            pivotVelocity = new Vector2(0, SNAKE_SPEED);
                        } else {
                            pivotVelocity = new Vector2(0, -1 * SNAKE_SPEED);
                        }

                        setPivots(pivotPosition, pivotVelocity);
                    }
                }

                // turn right or left
                if (direction == Swipe.RIGHT || direction == Swipe.LEFT) {
                    // make sure the snake isn't moving right or left
                    if (headVel.x == 0) {
                        // adjust the pivot position for next frame
                        if (headVel.y > 0) {
                            pivotPosition.y += 5;
                        } else {
                            pivotPosition.y -= 5;
                        }

                        // get the pivot velocity
                        Vector2 pivotVelocity;
                        if (direction == Swipe.RIGHT) {
                            pivotVelocity = new Vector2(SNAKE_SPEED, 0);
                        } else {
                            pivotVelocity = new Vector2(-1 * SNAKE_SPEED, 0);
                        }

                        setPivots(pivotPosition, pivotVelocity);
                    }
                }
            }

            public void setPivots(Vector2 pivotPosition, Vector2 pivotVelocity) {
                for (SnakePiece piece : snakePieces) {
                    ArrayMap<Vector2, Vector2> newPivot =
                            new ArrayMap(1);
                    newPivot.put(pivotPosition, pivotVelocity);
                    piece.pivots.add(newPivot);
                }
            }
        }));
    }
}
