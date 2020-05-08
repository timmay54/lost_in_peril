package com.freebandz.lost_in_peril;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.freebandz.lost_in_peril.screens.MainMenuScreen;
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
    public static final short MINE_BIT = 64;


    //public static final short ;
	
	private OrthographicCamera cam;
	public StretchViewport viewport;
	
	@Override
	public void create () {

		if(Gdx.app.getType() == Application.ApplicationType.Android) {
			platformName = "android";
		}

		if(platformName.equals("android")){
			WIDTH = Gdx.graphics.getWidth();
			HEIGHT = Gdx.graphics.getHeight();
		}

		/* IF file does not already exist
		if(Gdx.app.getPreferences("highscore") == null){

		}*/


		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		viewport = new StretchViewport(WIDTH, HEIGHT, cam);
		viewport.apply();
		cam.position.set(WIDTH/2, HEIGHT/2,0);
		cam.update();
		if(MainMenuScreen.godMode){
			this.setScreen(new MainMenuScreen(this)); //This determines first screen shown. was: new GameScreen(this)
		}
		else{
			this.setScreen(new introSlides(this));
		}


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
