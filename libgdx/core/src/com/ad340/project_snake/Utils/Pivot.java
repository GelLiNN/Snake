package com.ad340.project_snake.Utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Kellan on 5/11/16.
 */
public class Pivot {

    // state
    public Vector2 keyPosition;
    public Vector2 keyVelocity;

    // constructor
    public Pivot(Vector2 pos, Vector2 vel) {
        this.keyPosition = pos;
        this.keyVelocity = vel;
    }
}
