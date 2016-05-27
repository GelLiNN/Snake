package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.ProjectSnake;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tim on 4/27/2016.
 * Created by Kellan on 4/10/2016.
 */
public class Boundary extends InteractiveObject {

    /**
     * Boundary constructor
     * @param world
     * @param bounds
     */
    public Boundary(World world, Rectangle bounds) {
        super(new Texture("snake-sprite.png"), world, bounds);
        fixture.setUserData(this);
        setCategoryFilter(ProjectSnake.BOUNDARY_BIT);
    }

    @Override
    public void onHit() {
        Gdx.app.log("Boundary", "Collision");

    }
}
