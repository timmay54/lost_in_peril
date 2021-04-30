/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen of Western Illinois University
 */

package com.freebandz.lost_in_peril.Sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.screens.GameScreen;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class enemyFighter extends BadGuy{

    public enum State {STANDING, LEFT, UP, DOWN, RIGHT}
    public enemyFighter.State currentState;
    public enemyFighter.State previousState;
    private Animation baddieUp;
    private Animation baddieDown;
    private Animation baddieLeft;
    private Animation baddieRight;
    private boolean runningRight;
    private float stateTimer;
    RayHandler rayHandler;
    PointLight light;
    private TextureRegion baddieStand;

    public enemyFighter(GameScreen screen, float x, float y, RayHandler rayHandler) {
        super(screen, x, y);
        this.rayHandler = rayHandler;
        //super.screen.getAtlas().findRegion("AH_SpriteSheet_People1");

        currentState = enemyFighter.State.STANDING;
        previousState = enemyFighter.State.STANDING;
        stateTimer = 0;
        runningRight = true;
        //ANIMATION INITIALIZATION
        //DOWN
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 6; i<9; i++){
            frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),i * 16,64, 16, 16));
        }
        frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),112,64, 16, 16));
        baddieDown = new Animation(0.1f,frames);
        frames.clear();

        //RUNNING UPWARDS
        for(int i = 6; i<9; i++){
            frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),i * 16,112, 16, 16));
        }
        frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),112,96, 16, 16));
        baddieUp = new Animation(0.1f,frames);
        frames.clear();

        //LEFT
		for(int i = 6; i<9; i++){
			frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),i * 16,80, 16, 16));
		}
        frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),112,80, 16, 16));
        baddieLeft = new Animation(0.1f,frames);
        frames.clear();

        //RIGHT
        for(int i = 6; i<9; i++){
            frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),i * 16,96, 16, 16));
        }
        baddieRight = new Animation(0.1f,frames);
        frames.clear();

        baddieStand = new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),112,64,16,16);

        setBounds(getX(),getY(),16/Lost_In_Peril.PPM,16/Lost_In_Peril.PPM);
        setRegion(baddieStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x- 16, b2body.getPosition().y - 16);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();
        TextureRegion region;
        switch(currentState){
            case UP:
                region = (TextureRegion) baddieUp.getKeyFrame(stateTimer, true);
                break;

            case LEFT:
                region = (TextureRegion) baddieLeft.getKeyFrame(stateTimer, true);
                break;

            case RIGHT:
                region = (TextureRegion) baddieRight.getKeyFrame(stateTimer, true);
                break;

            case DOWN:
                region = (TextureRegion) baddieDown.getKeyFrame(stateTimer, true);
                break;

            default:
                region = baddieStand;
                break;

        }

		/*
		if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
			region.flip(true, false);
			runningRight = false;
		}
		else if((b2body.getLinearVelocity().x > 0 || runningRight) &&region.isFlipX()){
			region.flip(true, false);
			runningRight = true;
		}

		 */



        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState(){
        if(b2body.getLinearVelocity().y > 0){
            return enemyFighter.State.UP;
        }
        else if(b2body.getLinearVelocity().x > 0){
            return enemyFighter.State.RIGHT;
        }
        else if(b2body.getLinearVelocity().x < 0){
            return enemyFighter.State.LEFT;
        }
        else if(b2body.getLinearVelocity().y < 0){
            return enemyFighter.State.DOWN;
        }
        else{
            return enemyFighter.State.STANDING;
        }
    }

    @Override
    protected void defineBadGuy(float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(1530, 300); //Exact coordinates, do not divide by PPM
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7 / Lost_In_Peril.PPM);

        //light = new box2dLight.PointLight(rayHandler, 1000, Color.WHITE,50/Lost_In_Peril.PPM,0f,0f);
        //light.attachToBody(b2body);

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("EnemyFighter");

        String motto = "lets get em";

        //collision detection stuff, dunno what it does tho????


        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/Lost_In_Peril.PPM, 5 / Lost_In_Peril.PPM), new Vector2(2/Lost_In_Peril.PPM, 5 / Lost_In_Peril.PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("head");
    }

    @Override
    public void onHit(){

    }
}
