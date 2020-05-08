package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.freebandz.lost_in_peril.Lost_In_Peril;

public class ScoreScreen implements Screen {
	
	Lost_In_Peril game;
	
	private final int BACK_BUTTON_Y = 50;
	private static final int BACK_BUTTON_WIDTH = 100;
	private static final int BACK_BUTTON_HEIGHT = 100;
	Texture backButton = new Texture("backButton.png");

	public ScoreScreen(Lost_In_Peril game) {
		this.game = game;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		
		int backButton_x = (Lost_In_Peril.WIDTH / 4) - (BACK_BUTTON_WIDTH / 2);
		game.batch.draw(backButton, backButton_x, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT); //LAST TWO VARIABLE WERE MISSING! Hard to hit sweetspot when image is so big and zone is so small!
		if(Gdx.input.getX() < backButton_x + BACK_BUTTON_WIDTH && Gdx.input.getX() > backButton_x) {
			//System.out.println("Good!");
			if(Lost_In_Peril.HEIGHT - Gdx.input.getY() < BACK_BUTTON_Y + BACK_BUTTON_HEIGHT && Lost_In_Peril.HEIGHT - Gdx.input.getY() > BACK_BUTTON_Y) {
				//System.out.println("GREAT!");
				if(Gdx.input.isTouched()) {
					game.setScreen(new MainMenuScreen(game));
				}
			}
		}
		
		
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
		game.batch.dispose();
	}

}
