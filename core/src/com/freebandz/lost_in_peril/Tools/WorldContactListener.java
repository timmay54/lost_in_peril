package com.freebandz.lost_in_peril.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		Gdx.app.log("wall", "Yo begin");
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		Gdx.app.log("wall", "Yo end");
	}
	
	
	//Do not use either method below yet
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

		
	}
	
}
