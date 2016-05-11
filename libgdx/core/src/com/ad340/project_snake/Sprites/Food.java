package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.ProjectSnake;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tim on 4/27/2016.
 * Created by Kellan on 4/10/2016.
 */
public class Food extends Sprite {

    // constants
    public static final int FOOD_FIXTURE_RADIUS = 10;

    // state
    public World world;
    public Body b2body;

    /**
     * Food constructor
     */
    public Food(World world) {
        super(new Texture("food-sprite.png"));
        this.world = world;
        defineFood();
        setBounds(0, 0, 32 / ProjectSnake.PPM, 32 / ProjectSnake.PPM);
    }

    /**
     *
     * @param dt
     */
    public void update(float dt) {
        // set the sprite to the position of the box2d body
        float thisPosX = this.b2body.getWorldCenter().x,
                thisPosY = this.b2body.getWorldCenter().y;

        setPosition(thisPosX - getWidth() / 2, thisPosY - getHeight() / 2);
    }

    /**
     * Place the food
     */
    public void defineFood() {
        // get a random position
        float x = 300;
        float y = 300;

        // create a body definition for box2d
        BodyDef bdef = new BodyDef();
        bdef.position.set(x / ProjectSnake.PPM, y / ProjectSnake.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        this.b2body = world.createBody(bdef);

        // create a fixture definition for box2d
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(FOOD_FIXTURE_RADIUS / ProjectSnake.PPM);
        fdef.shape = shape;
        this.b2body.createFixture(fdef);
    }
}
