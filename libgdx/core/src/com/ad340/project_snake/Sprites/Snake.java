package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.ProjectSnake;
import com.ad340.project_snake.Screens.PlayScreen;
import com.ad340.project_snake.SwipeGestureDetector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

/**
 * Created by MasterOfTheUniverse on 4/21/16.
 */
public class Snake {

    // constants
    public static final float SNAKE_SPEED = 3f;

    // state
    public List<SnakePiece> snakePieces;

    public Snake(World world, PlayScreen screen) {
        snakePieces = new ArrayList<SnakePiece>();

        SnakePiece head = new SnakePiece(world, screen);
        snakePieces.add(head);

        setupGestures();
    }

    public void setupGestures() {
        // setup gestures
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

    /**
     * Update all the snakepieces in this snake with the same dt
     * @param dt
     */
    public void update(float dt) {
        for (SnakePiece piece : snakePieces) {
            piece.update(dt);
        }
    }

    public void draw(SpriteBatch batch) {
        for (SnakePiece piece : snakePieces) {
            piece.draw(batch);
        }
    }
}
