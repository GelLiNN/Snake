package com.ad340.project_snake.Utils;

import com.ad340.project_snake.Sprites.InteractiveObject;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by Tim on 5/10/2016.
 */
public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData().equals("head") || fixB.getUserData().equals("head")) {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if (object.getUserData() instanceof Sprite) {
                // object is a boundary, snake piece or food
                ((InteractiveObject) object.getUserData()).onHit();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        // Don't need to implement this
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // Don't need to implement this
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // Don't need to implement this
    }
}
