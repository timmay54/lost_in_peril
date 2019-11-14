package com.freebandz.lost_in_peril.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.screens.GameScreen;

public class Randy extends Sprite{
	public World world;
	public Body b2body;
	//private GameScreen screen;
	private TextureRegion randyStand;

	
	public Randy(World world, GameScreen screen) {
		super(screen.getAtlas().findRegion("Sprites"));
		this.world = world;
		defineRandy();
		randyStand = new TextureRegion(getTexture(),0,0,16,16);
		setBounds(0,0,16/Lost_In_Peril.PPM,16/Lost_In_Peril.PPM);
		setRegion(randyStand);

	}

	public void update(float dt){
		setPosition(b2body.getPosition().x- 8, b2body.getPosition().y - 8);
	}
	
	public void defineRandy() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(1800,360);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(10 / Lost_In_Peril.PPM);

		fdef.shape = shape;
		b2body.createFixture(fdef).setUserData("Randy");

		String motto = "lets get em";
		
		//collison detection stuff, dunno what it does tho????

		EdgeShape head = new EdgeShape();
		head.set(new Vector2(-2/Lost_In_Peril.PPM, 5 / Lost_In_Peril.PPM), new Vector2(2/Lost_In_Peril.PPM, 5 / Lost_In_Peril.PPM));
		fdef.shape = head;
		fdef.isSensor = true;
		b2body.createFixture(fdef).setUserData("head");
		
	}
}
