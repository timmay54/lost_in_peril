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
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.screens.GameScreen;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Randy extends Sprite{
	public enum State {STANDING, RUNNING, UP,LEFT,RIGHT,DOWN}
	public State currentState;
	public State previousState;
	private Animation randyUp;
	private Animation randyLeft;
	private Animation randyRight;
	private Animation randyDown;
	private boolean runningRight;
	private float stateTimer;
	public World world;
	public Body b2body;
	private TextureRegion randyStand;
	private PointLight randyLight;
	private PointLight randyLight2;
	public RayHandler rayHandler;

	
	public Randy(World world, GameScreen screen, int x, int y, RayHandler rayHandler) {
		super(screen.getAtlas().findRegion("AH_SpriteSheet_People1"));
		this.world = world;
		this.rayHandler = rayHandler;
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;
		randyLight = new box2dLight.PointLight(new RayHandler(world), 1000, Color.ORANGE,100f,1580f,350f);
		//ANIMATION INITIALIZATION
		//DOWN
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int i = 0; i<3; i++){
			frames.add(new TextureRegion(getTexture(),i * 16,0, 16, 16));
		}
		frames.add(new TextureRegion(getTexture(),16,0, 16, 16));
		randyDown = new Animation(0.1f,frames);
		frames.clear();

		//RUNNING UPWARDS
		for(int i = 0; i<3; i++){
			frames.add(new TextureRegion(getTexture(),i * 16,48, 16, 16));
		}
		frames.add(new TextureRegion(getTexture(),16,48, 16, 16));
		randyUp = new Animation(0.1f,frames);
		frames.clear();

		//LEFT
		/*for(int i = 1; i<3; i++){
			frames.add(new TextureRegion(getTexture(),i * 16,16, 16, 16));
		}*/
		frames.add(new TextureRegion(getTexture(),0,16, 16, 16));
		frames.add(new TextureRegion(getTexture(),32,16, 16, 16));
		randyLeft = new Animation(0.1f,frames);
		frames.clear();

		//RIGHT
		for(int i = 1; i<3; i++){
			frames.add(new TextureRegion(getTexture(),i * 16,32, 16, 16));
		}
		randyRight = new Animation(0.1f,frames);
		frames.clear();

		randyStand = new TextureRegion(getTexture(),16,0,16,16);


		defineRandy(x, y);

		setBounds(0,0,16/Lost_In_Peril.PPM,16/Lost_In_Peril.PPM);
		setRegion(randyStand);

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
				region = (TextureRegion) randyUp.getKeyFrame(stateTimer, true);
				break;

			case LEFT:
				region = (TextureRegion) randyLeft.getKeyFrame(stateTimer, true);
				break;

			case RIGHT:
				region = (TextureRegion) randyRight.getKeyFrame(stateTimer, true);
				break;

			case DOWN:
				region = (TextureRegion) randyDown.getKeyFrame(stateTimer, true);
				break;

			default:
				region = randyStand;
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
	
	public void defineRandy(int x, int y) {
		BodyDef bdef = new BodyDef();
		bdef.position.set(x,y);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(8 / Lost_In_Peril.PPM);
		fdef.filter.categoryBits = Lost_In_Peril.RANDY_BIT;
		fdef.filter.maskBits = Lost_In_Peril.DEFAULT_BIT
                | Lost_In_Peril.CHEST_BIT
                | Lost_In_Peril.COIN_BIT
				| Lost_In_Peril.MINE_BIT
                | Lost_In_Peril.TELEPORTER_BIT;

		fdef.shape = shape;

		randyLight = new box2dLight.PointLight(rayHandler, 1000, Color.WHITE,50/Lost_In_Peril.PPM,0f,0f);
		randyLight2 = new box2dLight.PointLight(rayHandler, 1000, Color.WHITE,50/Lost_In_Peril.PPM,0f,0f);
		randyLight.attachToBody(b2body);
		randyLight2.attachToBody(b2body,0f,1f);

		//define what this light collides with in world
		randyLight.setContactFilter(Lost_In_Peril.DEFAULT_BIT, Lost_In_Peril.RANDY_BIT, Lost_In_Peril.CHEST_BIT);

		b2body.createFixture(fdef).setUserData("Randy");

		String motto = "lets get em";

		EdgeShape head = new EdgeShape();
		head.set(new Vector2(-2/Lost_In_Peril.PPM, 5 / Lost_In_Peril.PPM),
				new Vector2(2/Lost_In_Peril.PPM, 5 / Lost_In_Peril.PPM));
		fdef.shape = head;
		fdef.isSensor = true;
		b2body.createFixture(fdef).setUserData("head");
		
	}

	/*public void teleportRandy(int x, int y){

	}*/
}
