package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.freebandz.lost_in_peril.Lost_In_Peril;

public class MainMenuScreen implements Screen{

	Lost_In_Peril game;
	
	public MainMenuScreen(Lost_In_Peril game) {
		this.game = game;
		//5min Video 4
		
	}
	
	@Override
	public void show() {
				
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.end();
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
		
	}

}
