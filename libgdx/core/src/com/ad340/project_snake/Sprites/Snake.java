package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.ProjectSnake;
import com.ad340.project_snake.Screens.PlayScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.List;

/**
 * Created by MasterOfTheUniverse on 4/21/16.
 */
public class Snake extends Sprite {
    public World world;
    public Body b2body;
    public List<Snake> children;

    public Snake(World world, PlayScreen screen) {
        super(new Texture("snake-sprite.png"));
        this.world = world;
        defineSnake();
        setBounds(0, 0, 16 / ProjectSnake.PPM, 16 / ProjectSnake.PPM);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void defineSnake() {
        // create a bodydef
        BodyDef bdef = new BodyDef();
        bdef.position.set(200, 200);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        // create a fixturedef
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(8);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
