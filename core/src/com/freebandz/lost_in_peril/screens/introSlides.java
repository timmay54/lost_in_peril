package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.freebandz.lost_in_peril.Lost_In_Peril;

public class introSlides implements Screen{
	
	Lost_In_Peril game;
	Texture slide1;
	Texture slide2;
	Texture slide3;
	
	public introSlides(Lost_In_Peril game) {
		this.game = game;
		slide1 = new Texture("485_550x550_Front_Color-NA.jpg");
		slide2 = new Texture("path/to/file.png");
		slide3 = new Texture("path/to/file.png");
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		
		//while loop
			//after 4 seconds, dim screen, change slide 1 to slide 2
			//repeat for slide 3 transition
			//if slide 3
				//dim screen and then -> this.set
		game.batch.draw(slide1,0,0);
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
