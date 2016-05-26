package com.ad340.project_snake.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tim on 4/27/2016.
 * Created by Kellan on 4/10/2016.
 */
public class Food extends InteractiveTileObject {

    /**
     * Food constructor
     * @param world
     * @param map
     * @param bounds
     */
    public Food(World world, TiledMap map, Rectangle bounds) {
        super(new Texture("food-sprite.png"), world, map, bounds);
        fixture.setUserData(this);
        fixture.setSensor(true);
    }

    @Override
    public void onHit() {
        Gdx.app.log("Food", "Collision");
    }
}
