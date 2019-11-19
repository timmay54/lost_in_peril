package com.freebandz.lost_in_peril;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.freebandz.lost_in_peril.screens.introSlides;

public class Lost_In_Peril extends Game {
	public static String platformName = "nulls";
	public SpriteBatch batch;
	//change this for screen
	public static int WIDTH = 1163;
	public static int HEIGHT = 720;
	public static final float PPM = .5f;

    public static final short DEFAULT_BIT = 1;
	public static final short RANDY_BIT = 2;
    public static final short CHEST_BIT = 4;
    public static final short COIN_BIT = 8;
    public static final short DESTROYED_BIT = 16;
    public static final short TELEPORTER_BIT = 32;
    //public static final short ;
	
	private OrthographicCamera cam;
	public StretchViewport viewport;
	
	@Override
	public void create () {

		if(platformName.equals("android")){
			WIDTH = Gdx.graphics.getWidth();
			HEIGHT = Gdx.graphics.getHeight();
		}

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
