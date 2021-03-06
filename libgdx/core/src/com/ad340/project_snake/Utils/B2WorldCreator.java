package com.ad340.project_snake.Utils;

import com.ad340.project_snake.Sprites.Boundary;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tim on 4/27/2016.
 * Created by Kellan on 4/10/2016.
 */
public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map) {
        // get boundary fixtures from layer 1 "boundaries" layer
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle bounds = ((RectangleMapObject) object).getRectangle();

            new Boundary(world, bounds);
        }
    }
}
