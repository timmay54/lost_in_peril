package com.freebandz.lost_in_peril.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.freebandz.lost_in_peril.screens.GameScreen;

public abstract class BadGuy extends Sprite{
	protected World world;
	protected GameScreen screen;
	public Body b2body;

	public BadGuy(GameScreen screen, float x, float y) {
		this.world = screen.getWorld();
		this.screen = screen;
		setPosition(x,y);
		defineBadGuy(x, y);

	}

	protected abstract void defineBadGuy(float x, float y);
	public abstract void onHit();
}
