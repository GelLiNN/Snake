package com.ad340.project_snake.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tim on 4/27/2016.
 * Created by Kellan on 4/10/2016.
 */
public class Boundary extends InteractiveTileObject {
    public Boundary(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }
}
