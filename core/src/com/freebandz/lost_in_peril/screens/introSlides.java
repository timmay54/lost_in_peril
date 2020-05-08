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
	Texture slide4;
	private float timeCount;
	
	public introSlides(Lost_In_Peril game) {
		this.game = game;
		//slides[5] = new Array<Texture>();
		slide1 = new Texture("485_550x550_Front_Color-NA.jpg");
		slide2 = new Texture("wiu.jpg");
		slide3 = new Texture("nullpointer.jpg");
		slide4 = new Texture("indiegamething.png");
		slides.add(slide1);
		slides.add(slide2);
		slides.add(slide3);
		slides.add(slide4);
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
			if(x == 4){
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

		if(x<=2){
			game.batch.draw(slides.get(x),Lost_In_Peril.WIDTH / 6,Lost_In_Peril.HEIGHT / 5);
		}
		else{
			game.batch.draw(slides.get(x), 5,  5, Lost_In_Peril.WIDTH * .85f, Lost_In_Peril.HEIGHT * .85f);
		}

		//TODO change above call to:
		//game.batch.draw(slides.get(x),Lost_In_Peril.WIDTH / 6,Lost_In_Peril.HEIGHT / 5, float WIDTH, float HEIGHT);

		//game.batch.draw(slide1,0,0);
		game.batch.end();
		update(delta);
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
