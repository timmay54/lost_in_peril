package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.freebandz.lost_in_peril.Lost_In_Peril;

public class introSlides implements Screen{
	
	Lost_In_Peril game;
	Array<Texture> slides = new Array<>(5);
	Texture slide1;
	Texture slide2;
	Texture slide3;
	private float timeCount;
	
	public introSlides(Lost_In_Peril game) {
		this.game = game;
		//slides[5] = new Array<Texture>();
		slide1 = new Texture("485_550x550_Front_Color-NA.jpg");
		slide2 = new Texture("cob_how.jpg");
		slide3 = new Texture("cob_how.gif");
		slides.add(slide1);
		slides.add(slide2);
		slides.add(slide3);
	}
	int x = 0;

	@Override
	public void show() {
		
	}

	public void update(float dt) {
		//countDownLabel.setText(String.format("%03d", worldTimer));
		timeCount += dt;
		if(timeCount >= 3){
			x++;
			if(x == 3){
				game.setScreen(new MainMenuScreen(game));
			}
			timeCount = 0;
		}

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		
		//while loop
			//after 4 seconds, dim screen, change slide 1 to slide 2
			//repeat for slide 3 transition
			//if slide 3
				//dim screen and then -> this.set

		game.batch.draw(slides.get(x),Lost_In_Peril.WIDTH / 4,Lost_In_Peril.HEIGHT / 4);

		//game.batch.draw(slide1,0,0);
		game.batch.end();
		update(delta);
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
