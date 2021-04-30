/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen of Western Illinois University
 */

package com.freebandz.lost_in_peril.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.screens.GameScreen;

public class Mine extends BadGuy{
    //Inits
    private TextureAtlas atlas;
    private Animation img;
    private float x;
    private float y;

    public Mine(GameScreen screen, float x, float y){
        super(screen,x,y);
        this.x = x;
        this.y = y;
        atlas = new TextureAtlas("dungeonPack.atlas");

        Array<TextureRegion> frames = new Array<>();
        frames.add(new TextureRegion(atlas.findRegion("Dungeon Collectables"), 0,  3, 16, 16));
        img = new Animation(1f,frames);

        //setPosition(x,y);x
        setBounds(getX(),getY(),16/Lost_In_Peril.PPM, 16/Lost_In_Peril.PPM);
        setRegion(frames.first());

    }

    public void update(float dt){
        //setPosition(b2body.getPosition().x, b2body.getPosition().y);
        setRegion((TextureRegion) img.getKeyFrame(1));
        setPosition(b2body.getPosition().x- 16, b2body.getPosition().y - 16);
    }

    /*public void draw(Batch batch){

    }*/

    @Override
    public void onHit(){
        setRegion((TextureRegion) img.getKeyFrame(1));
    }

    @Override
    protected void defineBadGuy(float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y); //Exact coordinates, do not divide by PPM
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Lost_In_Peril.PPM);
        fdef.filter.categoryBits = Lost_In_Peril.MINE_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }
}
