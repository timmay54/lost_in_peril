package com.freebandz.lost_in_peril.Sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.screens.GameScreen;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Dragon extends BadGuy{

    @Override
    public void onHit() {

    }

    public enum State {STANDING, RUNNING, UP,LEFT,RIGHT,DOWN}
    public State currentState;
    public State previousState;
    private Animation dragonUp;
    private Animation dragonLeft;
    private Animation dragonRight;
    private Animation dragonDown;
    private boolean runningRight;
    private float stateTimer;
    public Body b2body;
    private TextureRegion dragonStand;
    private PointLight dragonLight;
    private PointLight dragonLight2;
    public RayHandler rayHandler;


    public Dragon(GameScreen screen, float x, float y, RayHandler rayHandler) {
        super(screen, x, y);
        this.rayHandler = rayHandler;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
        //dragonLight = new box2dLight.PointLight(new RayHandler(world), 1000, Color.ORANGE,100f,1580f,350f);
        //ANIMATION INITIALIZATION
        //DOWN
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i<3; i++){
            frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),i * 16,0, 16, 16));
        }
        frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),16,0, 16, 16));
        dragonDown = new Animation(0.1f,frames);
        frames.clear();

        //RUNNING UPWARDS
        for(int i = 0; i<3; i++){
            frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),i * 16,48, 16, 16));
        }
        frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),16,48, 16, 16));
        dragonUp = new Animation(0.1f,frames);
        frames.clear();

        //LEFT
		/*for(int i = 1; i<3; i++){
			frames.add(new TextureRegion(getTexture(),i * 16,16, 16, 16));
		}*/
        frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),0,16, 16, 16));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),32,16, 16, 16));
        dragonLeft = new Animation(0.1f,frames);
        frames.clear();

        //RIGHT
        for(int i = 1; i<3; i++){
            frames.add(new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),i * 16,32, 16, 16));
        }
        dragonRight = new Animation(0.1f,frames);
        frames.clear();

        dragonStand = new TextureRegion(screen.getAtlas().findRegion("AH_SpriteSheet_People1"),16,0,16,16);


        defineBadGuy(x, y);

        setBounds(0,0,16/Lost_In_Peril.PPM,16/Lost_In_Peril.PPM);
        setRegion(dragonStand);

    }

    public void update(float dt){
        setPosition(b2body.getPosition().x- 16, b2body.getPosition().y - 16);
        setRegion(getFrame(dt));
        //rayHandler.updateAndRender();
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();
        TextureRegion region;
        switch(currentState){
            case UP:
                region = (TextureRegion) dragonUp.getKeyFrame(stateTimer, true);
                break;

            case LEFT:
                region = (TextureRegion) dragonLeft.getKeyFrame(stateTimer, true);
                break;

            case RIGHT:
                region = (TextureRegion) dragonRight.getKeyFrame(stateTimer, true);
                break;

            case DOWN:
                region = (TextureRegion) dragonDown.getKeyFrame(stateTimer, true);
                break;

            default:
                region = dragonStand;
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
            return State.UP;
        }
        else if(b2body.getLinearVelocity().x > 0){
            return State.RIGHT;
        }
        else if(b2body.getLinearVelocity().x < 0){
            return State.LEFT;
        }
        else if(b2body.getLinearVelocity().y < 0){
            return State.DOWN;
        }
        else{
            return State.STANDING;
        }
    }
    @Override
    public void defineBadGuy(float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x,y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(8 / Lost_In_Peril.PPM);
        fdef.filter.categoryBits = Lost_In_Peril.DRAGON_BIT;
        fdef.filter.maskBits = Lost_In_Peril.DEFAULT_BIT
                | Lost_In_Peril.CHEST_BIT
                | Lost_In_Peril.COIN_BIT
                | Lost_In_Peril.MINE_BIT
                | Lost_In_Peril.TELEPORTER_BIT;

        fdef.shape = shape;

        //TODO dragonLight = new box2dLight.PointLight(rayHandler, 1000, Color.WHITE,50/Lost_In_Peril.PPM,0f,0f);
        //dragonLight2 = new box2dLight.PointLight(rayHandler, 1000, Color.WHITE,50/Lost_In_Peril.PPM,0f,0f);
        //dragonLight.attachToBody(b2body);
        //dragonLight2.attachToBody(b2body,0f,1f);

        //define what this light collides with in world
        //dragonLight.setContactFilter(Lost_In_Peril.DEFAULT_BIT, Lost_In_Peril.DRAGON_BIT, Lost_In_Peril.CHEST_BIT);

        b2body.createFixture(fdef).setUserData("dragon");

        String motto = "ARG";

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/Lost_In_Peril.PPM, 5 / Lost_In_Peril.PPM),
                new Vector2(2/Lost_In_Peril.PPM, 5 / Lost_In_Peril.PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("head");

    }

}
