package com.ad340.project_snake.Utils;

import com.ad340.project_snake.Sprites.Boundary;
import com.ad340.project_snake.Sprites.Food;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tim on 4/27/2016.
 */
public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map) {
        // get boundary fixtures from layer 1 "boundaries" layer
        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle bounds = ((RectangleMapObject) object).getRectangle();

            new Boundary(world, map, bounds);
        }

        // get food fixtures from layer 2 "food" layer
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle bounds = ((RectangleMapObject) object).getRectangle();

            new Food(world, map, bounds);
        }
    }
}