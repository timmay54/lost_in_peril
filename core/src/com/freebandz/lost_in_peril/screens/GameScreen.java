package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.freebandz.lost_in_peril.Lost_In_Peril;

public class GameScreen implements Screen{
	public static final float SPEED = 150;
	Texture img;
	Texture gameScreenBackground;
	Texture link;
	
	private HUD hud;
	
	float x = 172;
	float y = 90;

	Lost_In_Peril game;
	
	Sound gameScreenSound = Gdx.audio.newSound(Gdx.files.internal("PM_INFECTED_05.ogg"));
	
	public GameScreen(Lost_In_Peril game) {
		// TODO Auto-generated constructor stub
		this.game = game;
		
		long id = gameScreenSound.play(1.0f);
		
		hud = new HUD(game.batch);
		
		gameScreenSound.setLooping(id,true);
	}
	
	@Override
	public void show() {
		img = new Texture("badlogic.jpg");
		gameScreenBackground = new Texture("startGameBackground.png");
		link = new Texture("link-sprite-png-6.gif");
	}

	@Override
	public void render(float delta) {
		
		game.batch.setProjectionMatrix(hud.stageHud.getCamera().combined);
		
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			y+=SPEED * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			y-=SPEED * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x+=SPEED * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x-=SPEED * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			//pause menu
			//System.out.println("Sterling."); 
		}
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		game.batch.draw(gameScreenBackground, 0,0, 1163, 720);
		game.batch.draw(link, x, y,45,50);
		
		game.batch.end();
		hud.stageHud.draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		game.batch.dispose();
		img.dispose();
		
	}

}
