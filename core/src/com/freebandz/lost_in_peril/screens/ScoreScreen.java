package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freebandz.lost_in_peril.Lost_In_Peril;

public class ScoreScreen{
	
	//private final int BACK_BUTTON_Y = 50;
	//private final int BACK_BUTTON_WIDTH = 100;
	//private final int BACK_BUTTON_HEIGHT = 100;
	//Texture backButton = new Texture("backButton.png");

	private Skin skin;
	private Viewport viewport;
	private Window scoreWindow;
	public static Stage scoreStage;
	private Table table;

	//creation of score screen
	public ScoreScreen(SpriteBatch sbb) {
		skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas("skin.atlas"));

		viewport = new StretchViewport(Lost_In_Peril.WIDTH, Lost_In_Peril.HEIGHT, new OrthographicCamera());
		scoreStage = new Stage(viewport, sbb);

		Preferences pref = Gdx.app.getPreferences("gameSettings");
		int highScore = pref.getInteger("highscore");

		Gdx.input.setInputProcessor(scoreStage);

		table = new Table();
		table.row();
		scoreWindow = new Window("Score", skin);
		scoreWindow.setMovable(false);

		//High Score
		Label currHighScore = new Label("High Score: " + highScore, skin);
		table.add(currHighScore);
		table.add();
		table.row();
		table.row().pad(5);

		//Back Button
		TextButton back = new TextButton("Back", skin);
		table.add(back);
		table.setTransform(true);
		table.scaleBy(1f);
		table.center();
		table.row();

		scoreWindow.add(table);
		scoreWindow.pack();

		final float newWidth = 400, newHeight = 250;
		scoreWindow.setBounds((Gdx.graphics.getWidth() - newWidth ) / 2,
				(Gdx.graphics.getHeight() - newHeight ) / 2, newWidth , newHeight );

		back.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				MainMenuScreen.showScoreScreen = false;
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		scoreStage.addActor(scoreWindow);
	}

	public void update(float dt) {
		scoreWindow.setVisible(MainMenuScreen.showScoreScreen);
	}
}
