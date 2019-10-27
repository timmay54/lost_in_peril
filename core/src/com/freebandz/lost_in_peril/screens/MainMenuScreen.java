package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
//import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.mappings.Xbox;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.freebandz.lost_in_peril.Lost_In_Peril;

public class MainMenuScreen implements Screen{

	private static final int EXIT_BUTTON_WIDTH = 300;
	private static final int EXIT_BUTTON_HEIGHT = 150;
	private static final int PLAY_BUTTON_WIDTH = 330;
	private static final int PLAY_BUTTON_HEIGHT = 150;
	private static final int SCORE_BUTTON_WIDTH = 270;
	private static final int SCORE_BUTTON_HEIGHT = 90;
	private static final int SETTINGS_BUTTON_WIDTH = 100;
	private static final int SETTINGS_BUTTON_HEIGHT = 100;
	private static final int EXIT_BUTTON_Y = 75;
	private static final int PLAY_BUTTON_Y = 350;
	private static final int SCORE_BUTTON_Y = 220;
	private static final int SETTINGS_BUTTON_Y = 50;

	Lost_In_Peril game;
	Texture exitButtonActive;
	Texture exitButtonInactive;
	Texture playButtonActive;
	Texture playButtonInactive;
	Texture scoreButton;
	Texture settingsButton;
	Texture mainBackground;
	public static Controller pad;


	//Sound mainMenuScreenSound = Gdx.audio.newSound(Gdx.files.internal("PM_AR_125_Fm_A.ogg"));	//only ogg works from zip https://www.omgubuntu.co.uk/2017/05/simple-sound-converter-ubuntu

	public MainMenuScreen(Lost_In_Peril game) {
		this.game = game;
		mainBackground = new Texture("mainBackground1.png");
		playButtonActive = new Texture("play_button_active.png");
		playButtonInactive = new Texture("play_button_inactive.png");
		exitButtonActive = new Texture("exit_button_active.png");
		exitButtonInactive = new Texture("exit_button_inactive.png");
		scoreButton = new Texture("score.png");
		settingsButton= new Texture("settingsButton.png");


		//long id = mainMenuScreenSound.play(1.0f); //plays once
		//mainMenuScreenSound.stop(); probably should go into dispose
		//mainMenuScreenSound.setLooping(id,true);


		//Controller support, only for specific xbox controller
		pad = null;
		for (Controller c : Controllers.getControllers()) {
			  System.out.println(c.getName());
			  if(c.getName().contains("360")) {
				  pad = c;
			  }
			  else if(c.getName().contains("XBOX") || c.getName().contains("Xbox")) {
				  pad = c;
			  }
		}

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(mainBackground,0,0);



		//PLAY BUTTON:
		int play_x = Lost_In_Peril.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
		if(Gdx.input.getX() < play_x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > play_x && Lost_In_Peril.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT &&
				Lost_In_Peril.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {
			game.batch.draw(playButtonActive, play_x, PLAY_BUTTON_Y , PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()) {
				this.dispose();
				game.setScreen(new GameScreen(game));
			}
		}
		else{
			game.batch.draw(playButtonInactive, play_x, PLAY_BUTTON_Y , PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
		}

		if (Gdx.input.isTouched( )){
			game.setScreen(new GameScreen(game));
		}

		//SCORE BUTTON:
		int score_x = (Lost_In_Peril.WIDTH / 2) - (SCORE_BUTTON_WIDTH / 2) - 70;
		game.batch.draw(scoreButton, score_x, SCORE_BUTTON_Y /*, width, height, srcX, srcY, srcWidth, srcHeight*/);
		if(Gdx.input.getX() < score_x + SCORE_BUTTON_WIDTH + 151 && Gdx.input.getX() > score_x && Lost_In_Peril.HEIGHT - Gdx.input.getY() < SCORE_BUTTON_Y + SCORE_BUTTON_HEIGHT &&
				Lost_In_Peril.HEIGHT - Gdx.input.getY() > SCORE_BUTTON_Y) {
			if(Gdx.input.isTouched()) {
				//mainMenuScreenSound.stop();
				game.setScreen(new ScoreScreen(game));
			}
		}


		//EXIT BUTTON:
		int exit_x = Lost_In_Peril.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
		if(Gdx.input.getX() < exit_x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > exit_x && Lost_In_Peril.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT &&
				Lost_In_Peril.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
			game.batch.draw(exitButtonActive, exit_x, EXIT_BUTTON_Y , EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()) {
				Gdx.app.exit();
			}
		}

		else{
			game.batch.draw(exitButtonInactive, exit_x, EXIT_BUTTON_Y , EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
		}

		//SETTINGS BUTTON:
		int settings_x = (Lost_In_Peril.WIDTH / 4) * 3 - (SETTINGS_BUTTON_WIDTH / 2) + 200;
		game.batch.draw(settingsButton, settings_x, SETTINGS_BUTTON_Y, SETTINGS_BUTTON_WIDTH, SETTINGS_BUTTON_HEIGHT); //LAST TWO VARIABLE WERE MISSING! Hard to hit sweetspot when image is so big and zone is so small!
		if(Gdx.input.getX() < settings_x + SETTINGS_BUTTON_WIDTH && Gdx.input.getX() > settings_x) {
			//System.out.println("Good!");
			if(Lost_In_Peril.HEIGHT - Gdx.input.getY() < SETTINGS_BUTTON_Y + SETTINGS_BUTTON_HEIGHT && Lost_In_Peril.HEIGHT - Gdx.input.getY() > SETTINGS_BUTTON_Y) {
				//System.out.println("GREAT!");
				if(Gdx.input.isTouched()) {
					//mainMenuScreenSound.stop();
					game.setScreen(new SettingsScreen2(game));
				}
			}
		}


		//PLAY WITH CONTROLLLER
		if(pad != null) {
			if(pad.getButton(Xbox.START)) {
				this.dispose();
				game.setScreen(new GameScreen(game));
			}

			//testing values of API
			if(pad.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) != 0) {
				System.out.println(pad.getButton((Xbox.L_BUMPER)));
				System.out.println(pad.getButton(Xbox.L_STICK));
				System.out.println(pad.getAxis(Xbox.R_STICK_HORIZONTAL_AXIS));
			}
		}




		/*
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			//pause menu
			System.out.println(settings_x);
		}
		if(Gdx.input.isKeyPressed(Keys.P)) {
			System.out.println(Gdx.input.getY());
		}*/

		game.batch.end();


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
		//mainMenuScreenSound.stop();
	}

}
