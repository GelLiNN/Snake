package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.ProjectSnake;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Tim on 5/25/2016.
 */
public class FoodController {

    // constants
    private static final float FOOD_SIZE = 20;

    // state
    private World world;
    private TiledMap map;
    private Snake snake;
    private Rectangle bounds;
    private Food food;
    private boolean foodIsPresent;

    public FoodController(World world, TiledMap map, Snake snake) {
        this.world = world;
        this.map = map;
        this.snake = snake;
        foodIsPresent = false;
    }

    private void addNewFood() {
        bounds = findNewBounds();
        food = new Food(world, this, bounds);
        foodIsPresent = true;
    }

    private Rectangle findNewBounds() {
        List<SnakePiece> snakePieces = new ArrayList<SnakePiece>(snake.getSnakePieces());
        List<Vector2> snakeLocations = new ArrayList<Vector2>();

        for (SnakePiece piece : snakePieces) {
            Vector2 loc = new Vector2(piece.b2body.getWorldCenter().x, piece.b2body.getWorldCenter().y);
            snakeLocations.add(loc);
        }

        float spawnX = 0, spawnY = 0;
        boolean isValidLoc = false;
        while (!isValidLoc) {
            spawnX = new Random().nextInt(
                    ProjectSnake.V_WIDTH
//                    300
            );
            spawnY = new Random().nextInt(
                    ProjectSnake.V_HEIGHT
//                    300
            );
            isValidLoc = true;

            for (Vector2 location : snakeLocations) {
                if (Math.abs(spawnX - location.x) < 16 && Math.abs(spawnY - location.y) < 16) {
                    isValidLoc = false;
                    break;
                }
            }
        }

        return new Rectangle(spawnX, spawnY, FOOD_SIZE, FOOD_SIZE);
    }

    public void consumeFood() {
        food = null;
        foodIsPresent = false;
        //snake.addToSnake();
    }

    /**
     * Update our food
     * @param dt
     */
    public void update(float dt) {
        // Check if we need to add more food
        if (!foodIsPresent) {
            addNewFood();
        }

        // set the sprite to the position of the box2d body
        float thisPosX = food.b2body.getWorldCenter().x,
              thisPosY = food.b2body.getWorldCenter().y;
        food.setPosition(thisPosX - food.getWidth() / 2, thisPosY - food.getHeight() / 2);
    }

    /**
     * Draws the food
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        food.draw(batch);
    }
}
