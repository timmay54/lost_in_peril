/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen of Western Illinois University
 */

package com.freebandz.lost_in_peril.Sprites;

import com.badlogic.gdx.audio.Sound;
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
import com.freebandz.lost_in_peril.screens.MainMenuScreen;

public class Coin extends BadGuy{
    public TextureAtlas atlas;
    private Animation img;
    private float x;
    private float y;
    private boolean setToDestroy;
    private boolean destroyed;

    public Coin(GameScreen screen, float x, float y){
        super(screen,x,y);
        //super(screen,x,y);
        this.x = x;
        this.y = y;
        atlas = new TextureAtlas("dungeonPack.atlas");

        setToDestroy = false;
        destroyed = false;
        Array<TextureRegion> frames = new Array<>();
        frames.add(new TextureRegion(atlas.findRegion("Dungeon Collectables"), 32,  6 * 21, 32, 32));
        img = new Animation(1f,frames);

        //setPosition(x,y);x
        setBounds(getX(),getY(),16/ Lost_In_Peril.PPM, 16/Lost_In_Peril.PPM);
        setRegion(frames.first());

    }

    public void update(float dt){
        //setPosition(b2body.getPosition().x, b2body.getPosition().y);


        if(setToDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
            //setRegion(new TextureRegion(atlas.findRegion("dungeonPack.atlas"), 6*21,6*21,1,1));
            setRegion((TextureRegion) img.getKeyFrame(1));
        }
        else if(!destroyed){
            setRegion((TextureRegion) img.getKeyFrame(1));
            setPosition(b2body.getPosition().x- 16, b2body.getPosition().y - 16);
        }
    }

    @Override
    protected void defineBadGuy(float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y); //Exact coordinates, do not divide by PPM
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(3 / Lost_In_Peril.PPM);
        fdef.filter.categoryBits = Lost_In_Peril.COIN_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void draw(Batch batch){
        if(!destroyed){
            super.draw(batch);
        }
    }

    @Override
    public void onHit(){
        setToDestroy = true;
        //screen.getManager().get("PM_HD_Designed_03_Doors_Old_Wooden_Scary_Horror_Open_Creeking.wav", Sound.class).play(MainMenuScreen.musicVolume);
    }
}
