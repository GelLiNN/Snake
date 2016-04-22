package com.ad340.project_snake.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by MasterOfTheUniverse on 4/21/16.
 */
public class Snake extends Sprite {
    public World world;
    public Body b2body;

    public Snake(World world) {
        this.world = world;
        defineSnake();
    }

    public void defineSnake() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(200, 200);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
