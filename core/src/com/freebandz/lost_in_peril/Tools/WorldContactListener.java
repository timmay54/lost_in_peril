package com.freebandz.lost_in_peril.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.freebandz.lost_in_peril.Sprites.InteractiveTileObject;
import com.freebandz.lost_in_peril.screens.GameScreen;

public class WorldContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();

		if(fixA.getUserData() == "Randy" || fixB.getUserData() == "Randy"){
			Fixture randy = fixA.getUserData() == "Randy" ? fixA : fixB;
			Fixture object = randy == fixA ? fixB : fixA;

			if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
				((InteractiveTileObject) object.getUserData()).onHit();
			}

			if(fixA.getUserData() == "Wall" || fixB.getUserData() == "Wall"){
				System.out.println("Randy + wall");
			}

			if(fixA.getUserData() == "EnemyFighter" || fixB.getUserData() == "EnemyFighter"){
				System.out.println("Randy + Enemy Fighter");
			}
			if(fixA.getUserData() == "Chest" || fixB.getUserData() == "Chest"){
				((InteractiveTileObject) object.getUserData()).onHit();
				//open chest sound
			}

			if(fixA.getUserData() == "Teleporter" || fixB.getUserData() == "Teleporter"){
				System.out.println(randy.getBody().getPosition());
				randy.getBody().setTransform(1794, 400, randy.getBody().getAngle());
				randy.getBody().setActive(true);
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
	}
	
	
	//Do not use either method below yet
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

		
	}
	
}
