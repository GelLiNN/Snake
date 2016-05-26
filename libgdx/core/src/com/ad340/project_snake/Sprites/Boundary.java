package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.Screens.PlayScreen;
import com.ad340.project_snake.Utils.ProjectSnake;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tim on 4/27/2016.
 * Created by Kellan on 4/10/2016.
 */
public class Boundary extends InteractiveTileObject {

    /**
     * Boundary constructor
     * @param world
     * @param map
     * @param bounds
     */
    public Boundary(World world, TiledMap map, Rectangle bounds) {
        super(new Texture("transparent.png"), world, map, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void onHit() {
        Gdx.app.log("Boundary", "Collision");
    }
}
