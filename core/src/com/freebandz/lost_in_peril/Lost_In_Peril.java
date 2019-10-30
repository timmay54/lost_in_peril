package com.freebandz.lost_in_peril;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.freebandz.lost_in_peril.screens.MainMenuScreen;
import com.freebandz.lost_in_peril.screens.introSlides;

public class Lost_In_Peril extends Game {
	public SpriteBatch batch;
	//change this for screen
	public static final int WIDTH = 1163;
	public static final int HEIGHT = 720; 
	public static final float PPM = (float) .5;
	
	private OrthographicCamera cam;
	private StretchViewport viewport;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		viewport = new StretchViewport(WIDTH, HEIGHT, cam);
		viewport.apply();
		cam.position.set(WIDTH/2, HEIGHT/2,0);
		cam.update();
		
		this.setScreen(new introSlides(this)); //This determines first screen shown. was: new GameScreen(this)
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(cam.combined);
		super.render();
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		super.resize(width, height);
	}
}
