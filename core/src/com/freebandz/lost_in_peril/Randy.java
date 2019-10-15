package com.freebandz.lost_in_peril;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.freebandz.lost_in_peril.screens.GameScreen;

public class Randy extends Sprite{
	public World world;
	public Body b2body;
	//private GameScreen screen;
	
	public Randy(World world) { //GameScreen screen
		//this.screen = screen;
		this.world = world;
		defineRandy();
	}
	
	public void defineRandy() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(32,32);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(5);
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
	}
}
