package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.freebandz.lost_in_peril.Lost_In_Peril;

public class SettingsScreen2 implements Screen{
	
	Lost_In_Peril game;
	
	private Texture backButton;
	public Stage stage;
	
	public static Skin skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas("skin.atlas"));
	Table table = new Table();
	
	private TextButton back1;
	
	private static final int BACK_BUTTON_WIDTH = 100;
	private static final int BACK_BUTTON_HEIGHT = 100;
	
	public SettingsScreen2(final Lost_In_Peril game) {
		this.game = game;
		
		stage = new Stage(new ScreenViewport());
		
		//LABELS ON SCREENS https://libgdx.info/basic_screen/
		Label title = new Label("Title Screen", skin);
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*2/3);
        title.setWidth(Gdx.graphics.getWidth());
        stage.addActor(title);
		
		//TextButtons
        backButton = new Texture("backButton.png");
		back1 = new TextButton("TeStttingg", skin);
		back1.setWidth(Gdx.graphics.getWidth()/2);
        back1.setPosition(Gdx.graphics.getWidth()/2-back1.getWidth()/2,Gdx.graphics.getHeight()/2-back1.getHeight()/2);
        back1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        stage.addActor(back1);
	}
	
	
	@Override
	public void show() {
		// SHOW
		
		//this is needed for the textButton to work
		Gdx.input.setInputProcessor(stage);
		//https://stackoverflow.com/questions/38188457/textbutton-hover-option-or-something-similar
		//look at link above for hover options
		
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
		if(Gdx.input.getX() < 50 + BACK_BUTTON_WIDTH && Gdx.input.getX() > 50 && Lost_In_Peril.HEIGHT - Gdx.input.getY() < 50 + BACK_BUTTON_HEIGHT && 
				Lost_In_Peril.HEIGHT - Gdx.input.getY() > 50) {
			if(Gdx.input.isTouched()) {
				game.setScreen(new MainMenuScreen(game));
			}
		}
		
		/* TESTING
		
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
		
		stage.draw();
		
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
		//stage.dispose();
		game.batch.dispose();
	}

}