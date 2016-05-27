package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.ProjectSnake;
import com.ad340.project_snake.Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tim on 4/27/2016.
 * Created by Kellan on 4/10/2016.
 */
public class Food extends InteractiveObject {

    private FoodController controller;
    /**
     * Food constructor
     * @param world
     * @param controller
     * @param bounds
     */
    public Food(World world, FoodController controller, Rectangle bounds) {
        super(new Texture("food-sprite.png"), world, bounds);
        this.controller = controller;
        fixture.setUserData(this);
        fixture.setSensor(true);
        setCategoryFilter(ProjectSnake.FOOD_BIT);
        setBounds(0, 0, 32 / ProjectSnake.PPM, 32 / ProjectSnake.PPM);
    }

    @Override
    public void onHit() {
        Gdx.app.log("Food", "Collision");
        setCategoryFilter(ProjectSnake.DESTROYED_BIT);
        controller.consumeFood();
        Hud.addScore(100);
    }
}
