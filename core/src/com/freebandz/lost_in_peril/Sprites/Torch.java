/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen of Western Illinois University
 */

package com.freebandz.lost_in_peril.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.screens.GameScreen;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Torch extends BadGuy {

    public enum State { UP, DOWN, RIGHT}
    public enemyFighter.State currentState;
    public enemyFighter.State previousState;
    private Animation baddieDown;
    private float stateTimer;
    private TextureAtlas atlas;
    public RayHandler rayHandler;
    public PointLight light;
    private TextureRegion baddieStand;

    public Torch(GameScreen screen, float x, float y, RayHandler rayHandler) {
        super(screen, x, y);
        this.rayHandler = rayHandler;
        currentState = enemyFighter.State.STANDING;
        previousState = enemyFighter.State.STANDING;
        stateTimer = 0;
        atlas = new TextureAtlas(Gdx.files.internal("specialEffects.atlas"));

        //ANIMATION INITIALIZATION
        //DOWN
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i<7; i++){
            for(int j = 0; j<8; j++) {
                frames.add(new TextureRegion(atlas.findRegion("fire"), j * 100, i * 100, 100, 100));
            }
        }
        for(int i=0; i<5;i++){
            frames.add(new TextureRegion(atlas.findRegion("fire"), i * 100, 7 * 100, 100, 100));
        }
        //frames.add(new TextureRegion(atlas.findRegion("fire"),40,20, 16, 16));
        baddieDown = new Animation(0.01f,frames);
        frames.clear();

        setBounds(getX(),getY(),50/Lost_In_Peril.PPM,50/Lost_In_Peril.PPM);
        setPosition(x, y);
        light = new PointLight(rayHandler, 1000, Color.ORANGE,100/Lost_In_Peril.PPM,x,y);
    }

    public void update(float dt){
        setRegion(getFrame(dt));
        //rayHandler.updateAndRender(); // not needed, ruins FPS fosho any
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();
        TextureRegion region;

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region = (TextureRegion) baddieDown.getKeyFrame(stateTimer, true);
    }

    public enemyFighter.State getState(){
        return enemyFighter.State.DOWN;
    }

    @Override
    protected void defineBadGuy(float x, float y ) {
    }

    @Override
    public void onHit() {

    }
}
