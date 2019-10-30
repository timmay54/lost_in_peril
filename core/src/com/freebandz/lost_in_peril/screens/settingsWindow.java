package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freebandz.lost_in_peril.Lost_In_Peril;
//import com.badlogic.gdx.utils.*;

public class settingsWindow{

	private Skin skin;
	private Viewport viewport;
	private Window settings;
	public static Stage stage;
	private Table table;

	public settingsWindow(SpriteBatch sbb){
		skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas("skin.atlas"));

		viewport = new StretchViewport(Lost_In_Peril.WIDTH, Lost_In_Peril.HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sbb);

		Gdx.input.setInputProcessor(stage);

		table = new Table();
		table.row();
		settings = new Window("Settings", skin);
		settings.setMovable(false);
		TextButton increment = new TextButton("+", skin);

		//settings.add(increment); //Add a new text button that unpauses the game.
		TextButton decrement = new TextButton("-", skin);
		table.add(decrement);
		table.add();
		table.add(increment);
		table.row();
		table.row().pad(5);
		TextButton back = new TextButton("Back", skin);
		//settings.add(back, decrement);
		//back.scaleBy(1.5f);
		table.add(back);
		settings.add(table);
		settings.pack(); //Important! Correctly scales the window after adding new elements.
		float newWidth = 400, newHeight = 250;
		settings.setBounds((Gdx.graphics.getWidth() - newWidth ) / 2,
				(Gdx.graphics.getHeight() - newHeight ) / 2, newWidth , newHeight ); //Center on screen.



		increment.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				if(MainMenuScreen.musicVolume < 1f) {
					MainMenuScreen.musicVolume += .1f;
				}
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		decrement.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				if(MainMenuScreen.musicVolume >= 0f) {
					MainMenuScreen.musicVolume -= .1f;
				}
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		back.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				MainMenuScreen.showSettings = false;
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		stage.addActor(settings);
	}

	public void update(float dt) {
		settings.setVisible(MainMenuScreen.showSettings);
	}

}
