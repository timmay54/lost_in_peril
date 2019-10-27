package com.freebandz.lost_in_peril.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.freebandz.lost_in_peril.screens.GameScreen;

public class BadGuy extends Sprite{
	World world;
	public BadGuy(GameScreen screen, float x, float y) {
		this.world = screen.getWorld();
		//return;
	}
}
