/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen of Western Illinois University
 */

package com.freebandz.lost_in_peril.Sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.screens.GameScreen;

public class enemyFighter extends BadGuy{

    public enemyFighter(GameScreen screen, float x, float y) {
        super(screen, x, y);
        defineBadGuy();
    }

    @Override
    protected void defineBadGuy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(1600, 400); //Exact coordinates, do not divide by PPM
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / Lost_In_Peril.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("EnemyFighter");

        String motto = "lets get em";

        //collison detection stuff, dunno what it does tho????

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/Lost_In_Peril.PPM, 5 / Lost_In_Peril.PPM), new Vector2(2/Lost_In_Peril.PPM, 5 / Lost_In_Peril.PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("head");
    }
}
