package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.Utils.ProjectSnake;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tim on 4/27/2016.
 * Created by Kellan on 4/27/2016.
 */
public abstract class InteractiveTileObject extends Sprite {
    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body b2body;

    protected Fixture fixture;

    public InteractiveTileObject(Texture texture, World world, TiledMap map, Rectangle bounds) {
        super(texture);
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        // create a body definition for box2d
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / ProjectSnake.PPM, (bounds.getY() + bounds.getHeight() / 2)/ ProjectSnake.PPM);
        this.b2body = world.createBody(bdef);

        // create a fixture definition for box2d
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((bounds.getWidth() / 2) / ProjectSnake.PPM, (bounds.getHeight() / 2) / ProjectSnake.PPM);
        fdef.shape = shape;
        fixture = b2body.createFixture(fdef);

        // set the sprite to the position of the box2d body
        float thisPosX = this.b2body.getWorldCenter().x,
              thisPosY = this.b2body.getWorldCenter().y;
        setPosition(thisPosX - getWidth() / 2, thisPosY - getHeight() / 2);
    }

    public abstract void onHit();
}
