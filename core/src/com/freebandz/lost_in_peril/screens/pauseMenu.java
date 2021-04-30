package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freebandz.lost_in_peril.Lost_In_Peril;

import static com.freebandz.lost_in_peril.screens.GameScreen.boolPause;

public class pauseMenu {
	
	public Stage pauseStage;
	private Viewport viewportPause;
	public Window pauseWindow;

	//public static Skin skin = new Skin(Gdx.files.internal("core/assets/gdx-skins_newfolder/biological-attack/skin/biological-attack-ui.json"),
	//		new TextureAtlas(Gdx.files.internal("core/assets/gdx-skins_newfolder/biological-attack/skin/biological-attack-ui.atlas")));

	private Skin skin;

	public pauseMenu(SpriteBatch sbb){
		skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas("skin.atlas"));
		viewportPause = new FitViewport(Lost_In_Peril.WIDTH, Lost_In_Peril.HEIGHT, new OrthographicCamera());
		pauseStage = new Stage(viewportPause, sbb);
		Gdx.input.setInputProcessor(pauseStage);

		Table table = new Table();
		pauseWindow = new Window("Paused", skin);
		pauseWindow.setMovable(false);
		TextButton unpause = new TextButton("Unpause", skin);

		//table.center();
		table.add(unpause);
		table.row();
		TextButton mainMenu = new TextButton("Quit", skin);
		table.add(mainMenu);

		pauseWindow.add(table); //Add a new text button that unpauses the game.
		pauseWindow.pack(); //Important! Correctly scales the window after adding new elements.
		float newWidth = 400, newHeight = 250;
		pauseWindow.setBounds((Gdx.graphics.getWidth() - newWidth ) / 2,
		(Gdx.graphics.getHeight() - newHeight ) / 2, newWidth , newHeight ); //Center on screen.

		//pauseWindow.add(table);

		unpause.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                boolPause = false;
                //pauseWindow.setVisible(false);

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

		mainMenu.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				//GameScreen.returnToMain = true;
				Gdx.app.exit();

			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});



		pauseStage.addActor(pauseWindow);


		//pauseStage.addActor(unpause);
	}
	
	public void update(float dt) {
		pauseWindow.setVisible(boolPause);
		//unpause.setVisible(GameScreen.boolPause);
	}
}
