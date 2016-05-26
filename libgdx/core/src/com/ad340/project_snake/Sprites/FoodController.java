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
    private static final float FOOD_SIZE = 20;

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
        bounds = new Rectangle(190, 600, FOOD_SIZE, FOOD_SIZE);
    }

    /**
     * Check if we need to add more food
     * @param dt
     */
    public void update(float dt) {
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
