package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.ProjectSnake;
import com.ad340.project_snake.Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private Food food;
    private boolean foodIsPresent;
    private boolean spriteIsSet;


    int spawnCount = 0;

    public FoodController(World world, TiledMap map, Snake snake) {
        this.world = world;
        this.map = map;
        this.snake = snake;
        foodIsPresent = false;
        spriteIsSet = false;
    }

    private void addNewFood() {
        Rectangle bounds = findNewBounds();
        food = new Food(world, this, bounds);
        foodIsPresent = true;
    }

    private Rectangle findNewBounds() {
        float spawnX, spawnY;
        Rectangle spawnLoc = null;
        boolean isValidLoc = false;
        while (!isValidLoc) {
            isValidLoc = true;

            // subtracting and adding V_BORDER keeps the food within the borders of the map
            // the 28 exists because the sprite isn't centered in the spawnX/spawnY. it spawns in the bottom left
            spawnX = new Random().nextInt((ProjectSnake.V_WIDTH - ProjectSnake.V_BORDER - 28)
                    - ProjectSnake.V_BORDER) + ProjectSnake.V_BORDER;
            spawnY = new Random().nextInt((ProjectSnake.V_HEIGHT - ProjectSnake.V_BORDER - 28)
                    - ProjectSnake.V_BORDER) + ProjectSnake.V_BORDER;
            spawnLoc = new Rectangle(spawnX, spawnY, FOOD_SIZE, FOOD_SIZE);

            // make sure the food doesn't spawn on the snake
            List<SnakePiece> snakePieces = new ArrayList<SnakePiece>(snake.getSnakePieces());
            for (SnakePiece piece : snakePieces) {
                Rectangle bounds = piece.getBoundingRectangle();
                // Scale the bounds
                bounds.x *= ProjectSnake.PPM; bounds.y *= ProjectSnake.PPM;
                bounds.width *= ProjectSnake.PPM; bounds.height *= ProjectSnake.PPM;
                if (bounds.overlaps(spawnLoc)) {
                    Gdx.app.log("Food", "Spawned in snake"); // testing
                    isValidLoc = false;
                    break;
                }
            }

            // make sure the food doesn't spawn on boundaries
            for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle bounds = ((RectangleMapObject) object).getRectangle();
                if (bounds.overlaps(spawnLoc)) {
                    Gdx.app.log("Food", "Spawned in boundary"); // testing
                    isValidLoc = false;
                    break;
                }
            }
        }

        return spawnLoc;
    }

    public void consumeFood() {
        resetFood();
        snake.setAddStatus(true);
        Hud.addScore(100);
    }

    public void resetFood() {
        food.setCategoryFilter(ProjectSnake.DESTROYED_BIT);
        food = null;
        foodIsPresent = false;
        spriteIsSet = false;
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
        if (!spriteIsSet && foodIsPresent) {
            float thisPosX = food.b2body.getWorldCenter().x,
                    thisPosY = food.b2body.getWorldCenter().y;
            food.setPosition(thisPosX - food.getWidth() / 2, thisPosY - food.getHeight() / 2);
            spriteIsSet = true;
        }
    }

    /**
     * Draws the food
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        food.draw(batch);
    }
}
