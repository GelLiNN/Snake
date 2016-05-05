package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.ProjectSnake;
import com.ad340.project_snake.Screens.PlayScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.LinkedList;
import java.util.Queue;

import javafx.util.Pair;

/**
 * Created by MasterOfTheUniverse on 5/4/16.
 */
public class SnakePiece extends Sprite {

    // constants
    public static final int SNAKE_FIXTURE_RADIUS = 10;

    // movement data
    Queue<Pair<Vector2, Vector2>> pivots;

    // other snake data
    public World world;
    public Body b2body;

    public SnakePiece(World world, PlayScreen screen) {
        super(new Texture("snake-sprite.png"));
        pivots = new LinkedList<Pair<Vector2, Vector2>>();
        this.world = world;
        defineSnake();
        setBounds(0, 0, 32 / ProjectSnake.PPM, 32 / ProjectSnake.PPM);
    }

    public void update(float dt) {
        // setting the sprite to the position of the box2d body
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        if (!pivots.isEmpty()) {
            Vector2 thisPos = this.b2body.getWorldCenter();
            Pair<Vector2, Vector2> nextPivot = pivots.peek();

            if (nextPivot.getKey().epsilonEquals(thisPos, 0)) {
                nextPivot = pivots.remove();
                b2body.setLinearVelocity(0, 0);
                b2body.applyLinearImpulse(nextPivot.getValue(), this.b2body.getWorldCenter(), true);
            }
        }
    }

    public void defineSnake() {
        // create a body definition for box2d
        BodyDef bdef = new BodyDef();
        bdef.position.set(200/ ProjectSnake.PPM, 200/ ProjectSnake.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        // create a fixture definition for box2d
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(SNAKE_FIXTURE_RADIUS / ProjectSnake.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

}
