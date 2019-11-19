/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen of Western Illinois University
 */

package com.freebandz.lost_in_peril.Sprites;

import com.badlogic.gdx.Gdx;
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

public class Torch extends BadGuy {

    public enum State {STANDING, LEFT, UP, DOWN, RIGHT}
    public enemyFighter.State currentState;
    public enemyFighter.State previousState;
    private Animation baddieUp;
    private Animation baddieDown;
    private Animation baddieLeft;
    private Animation baddieRight;
    private boolean runningRight;
    private float stateTimer;
    private TextureAtlas atlas;

    private TextureRegion baddieStand;

    public Torch(GameScreen screen, float x, float y) {
        super(screen, x, y);

        currentState = enemyFighter.State.STANDING;
        previousState = enemyFighter.State.STANDING;
        stateTimer = 0;
        runningRight = true;
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
        setPosition(1615, 500);
    }

    public void update(float dt){
        //setPosition(b2body.getPosition().x - 50, b2body.getPosition().y - 50);
        setRegion(getFrame(dt));
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
    protected void defineBadGuy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(1615, 500); //Exact coordinates, do not divide by PPM
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(1 / Lost_In_Peril.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("EnemyFighter");
    }

    /*

    private Animation movingFire;
    private float stateTimer;
    public World world;
    public Body b2body;
    private TextureRegion region;
    private TextureAtlas atlas;

    public Torch(GameScreen screen, float x, float y) {
        super(screen,x,y);
        atlas = new TextureAtlas(Gdx.files.internal("specialEffects.atlas"));
        region = new TextureRegion(atlas.findRegion("fire"));
        stateTimer = 0;
        Array<TextureRegion> frames = new Array<>();
        for(int i = 1; i < 4; i++){
            frames.add(new TextureRegion(region.getTexture(),i * 16,0, 16, 16));
        }
        movingFire = new Animation(0.05f, frames);
        frames.clear();

        setBounds(getX(),getY(),16/ Lost_In_Peril.PPM,16/Lost_In_Peril.PPM);
        setRegion(region);
    }

    public void update(float dt){
        //setPosition();
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        return region = (TextureRegion) movingFire.getKeyFrame(stateTimer, true);
    }
    @Override
    protected void defineBadGuy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(1700, 400);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / Lost_In_Peril.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("Fire");
    }*/
}
