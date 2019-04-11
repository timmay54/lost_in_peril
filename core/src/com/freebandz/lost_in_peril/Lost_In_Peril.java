package com.freebandz.lost_in_peril;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.freebandz.lost_in_peril.screens.MainMenuScreen;

public class Lost_In_Peril extends Game {
	public SpriteBatch batch;
	//change this for screen
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this)); //This determines first screen shown. was: new GameScreen(this)
	}

	@Override
	public void render () {
		super.render();
	}
}
