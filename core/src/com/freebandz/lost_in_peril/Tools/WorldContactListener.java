package com.freebandz.lost_in_peril.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.Sprites.Coin;
import com.freebandz.lost_in_peril.Sprites.InteractiveTileObject;
import com.freebandz.lost_in_peril.screens.GameScreen;
import com.freebandz.lost_in_peril.screens.MainMenuScreen;

public class WorldContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();

		if(fixA.getUserData() == "Randy" || fixB.getUserData() == "Randy"){
			Fixture randy = fixA.getFilterData().categoryBits == Lost_In_Peril.RANDY_BIT ? fixA : fixB;
			Fixture object = randy == fixA ? fixB : fixA;

			int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

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
				if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
					((InteractiveTileObject) object.getUserData()).onHit();
					//open chest sound

					Sound chestNoise = Gdx.audio.newSound(Gdx.files.internal("PM_HD_Designed_03 Doors, Old, Wooden, Scary, Horror, Open, Creeking"));
					chestNoise.play(MainMenuScreen.musicVolume);
				}
			}

			if(fixA.getUserData() == "Teleporter" || fixB.getUserData() == "Teleporter"){
				System.out.println(randy.getBody().getPosition());
				//randy.getBody().setTransform(1794, 400, randy.getBody().getAngle());
				//randy.getBody().setActive(true);
				//randy = new player(coords);
			}

			if(fixA.getUserData() == "Dragon" || fixB.getUserData() == "Dragon"){
				GameScreen.hud.worldTimer = 0;
			}

			if(fixA.getFilterData().categoryBits == Lost_In_Peril.MINE_BIT || fixB.getFilterData().categoryBits == Lost_In_Peril.MINE_BIT){
				System.out.println(randy.getBody().getPosition());
				GameScreen.boolPause = true;
				GameScreen.hud.worldTimer = 0;
			}

			if(fixA.getFilterData().categoryBits == Lost_In_Peril.COIN_BIT || fixB.getFilterData().categoryBits == Lost_In_Peril.COIN_BIT){
				System.out.println(randy.getBody().getPosition());
				GameScreen.hud.addScore(25);
				((Coin)object.getUserData()).onHit();
				/*if(randy == fixA){
					//(Coin) object.destroy(true);
				}
				else{
					((Coin)fixA.getUserData()).onHit();
				}

				 */
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
