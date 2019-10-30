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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freebandz.lost_in_peril.Lost_In_Peril;

public class pauseMenu {
	
	public Stage pauseStage;
	private Viewport viewportPause;
	public Window pause;
	public static TextButton unpause;
	//public static Skin skin = new Skin(Gdx.files.internal("core/assets/gdx-skins_newfolder/biological-attack/skin/biological-attack-ui.json"),
	//		new TextureAtlas(Gdx.files.internal("core/assets/gdx-skins_newfolder/biological-attack/skin/biological-attack-ui.atlas")));
	private Skin skin;

	public pauseMenu(SpriteBatch sbb){
		skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas("skin.atlas"));
		viewportPause = new StretchViewport(Lost_In_Peril.WIDTH, Lost_In_Peril.HEIGHT, new OrthographicCamera());
		pauseStage = new Stage(viewportPause, sbb);
		//Table pauseTable = new Table();
		pause = new Window("Paused", skin);
		pause.setMovable(false);
		unpause = new TextButton("Unpause", skin);
		pause.add(unpause).center(); //Add a new text button that unpauses the game.
		pause.pack(); //Important! Correctly scales the window after adding new elements.
		float newWidth = 400, newHeight = 250;
		pause.setBounds((Gdx.graphics.getWidth() - newWidth ) / 2,
		(Gdx.graphics.getHeight() - newHeight ) / 2, newWidth , newHeight ); //Center on screen.



		unpause.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.boolPause = false;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });



		pauseStage.addActor(pause);


		pauseStage.addActor(unpause);
	}
	
	public void update(float dt) {
		pause.setVisible(GameScreen.boolPause);
		unpause.setVisible(GameScreen.boolPause);
	}
}
