package com.ad340.project_snake.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tim on 5/25/2016.
 */
public class FoodController {

    // constants
    private static final float FOOD_SIZE = 10;

    // state
    private World world;
    private TiledMap map;
    private Rectangle bounds;
    private Food food;
    private boolean foodIsPresent;

    public FoodController(World world, TiledMap map) {
        this.world = world;
        this.map = map;
        foodIsPresent = false;
    }

    private void addNewFood() {
        findNewBounds();
        food = new Food(world, map, bounds);
        foodIsPresent = true;
    }

    private void findNewBounds() {
        bounds = new Rectangle(190, 600, FOOD_SIZE / 100f, FOOD_SIZE / 100f);
    }

    /**
     * Check if we need to add more food
     * @param dt
     */
    public void update(float dt) {
        if (!foodIsPresent) {
            addNewFood();
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
