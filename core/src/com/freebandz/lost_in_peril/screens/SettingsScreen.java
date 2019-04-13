package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.freebandz.lost_in_peril.Lost_In_Peril;

public class SettingsScreen implements Screen{

	Lost_In_Peril game;
	
	Texture backButton;
	
	private static final int BACK_BUTTON_WIDTH = 100;
	private static final int BACK_BUTTON_HEIGHT = 100;
	
	public SettingsScreen(Lost_In_Peril game) {
		this.game = game;
		backButton = new Texture("backButton.png");
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		/* if volume icon is clicked:
		 *     swap icons between volume_icon_diasbled & volume_Icon_enabled
		 */
		
		game.batch.draw(backButton, 50,50,BACK_BUTTON_WIDTH,BACK_BUTTON_HEIGHT);
		if(Gdx.input.getX() < 50 + BACK_BUTTON_WIDTH && Gdx.input.getX() > BACK_BUTTON_WIDTH && Lost_In_Peril.HEIGHT - Gdx.input.getY() < 50 + BACK_BUTTON_HEIGHT && 
				Lost_In_Peril.HEIGHT - Gdx.input.getY() > 50) {
			if(Gdx.input.isTouched()) {
				game.batch.dispose();
			}
		}
		
		/* TESTING
		Skin skin = new Skin();
		Window pause = new Window("Paused", skin);
		//pause.setMoveable(false); //So the user can't move the window
		//pause.add(new TextButton("Unpause", skin)); //Add a new text button that unpauses the game.
		pause.pack(); //Important! Correctly scales the window after adding new elements.
		float newWidth = 400, newHeight = 200;
		pause.setBounds((Gdx.graphics.getWidth() - newWidth ) / 2,
		(Gdx.graphics.getHeight() - newHeight ) / 2, newWidth , newHeight ); //Center on screen.
		//stage.addActor(window);
		*/
		
		
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
