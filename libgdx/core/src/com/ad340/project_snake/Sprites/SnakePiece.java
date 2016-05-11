package com.ad340.project_snake.Sprites;

import com.ad340.project_snake.ProjectSnake;
import com.ad340.project_snake.Screens.PlayScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
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
    Queue<Pair<Vector2, Vector2>> pivots; // position, velocity

    // other snake data
    public World world;
    public Body b2body;

    /**
     * SnakePiece constructor
     * @param world to place this SnakePiece in
     * @param position to place this SnakePiece
     * @param velocity to give this SnakePiece when it spawns
     */
    public SnakePiece(World world, Vector2 position, Vector2 velocity) {
        super(new Texture("snake-sprite.png"));
        pivots = new LinkedList<Pair<Vector2, Vector2>>();
        this.world = world;
        defineSnakePiece(position, velocity);
        setBounds(0, 0, 32 / ProjectSnake.PPM, 32 / ProjectSnake.PPM);
    }

    /**
     * Update this SnakePiece's position and velocity
     * @param dt
     */
    public void update(float dt) {
        float thisPosX = this.b2body.getWorldCenter().x,
              thisPosY = this.b2body.getWorldCenter().y;

        // set the sprite to the position of the box2d body
        setPosition(thisPosX - getWidth() / 2, thisPosY - getHeight() / 2);

        // check pivots to turn the SnakePiece
        if (!pivots.isEmpty()) {
            Vector2 thisPos = new Vector2(thisPosX * ProjectSnake.PPM, thisPosY * ProjectSnake.PPM);
            Pair<Vector2, Vector2> nextPivot = pivots.peek();

            if (nextPivot.getKey().epsilonEquals(thisPos, 0.01f)) {
                // turn the SnakePiece
                nextPivot = pivots.remove();
                this.b2body.setLinearVelocity(0, 0);
                this.b2body.applyLinearImpulse(nextPivot.getValue(), this.b2body.getWorldCenter(), true);

                System.out.println("turned a piece");
            }
        }
    }

    /**
     * Give the SnakePiece a position and velocity
     * @param position
     * @param velocity
     */
    public void defineSnakePiece(Vector2 position, Vector2 velocity) {
        // create a body definition for box2d
        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x / ProjectSnake.PPM, position.y / ProjectSnake.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        this.b2body = world.createBody(bdef);

        // create a fixture definition for box2d
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(SNAKE_FIXTURE_RADIUS / ProjectSnake.PPM);
        fdef.shape = shape;
        this.b2body.createFixture(fdef);

        // start moving the piece
        this.b2body.applyLinearImpulse(velocity, this.b2body.getWorldCenter(), true);
    }

}
