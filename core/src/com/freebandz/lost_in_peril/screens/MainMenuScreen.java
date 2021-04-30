package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.mappings.Xbox;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freebandz.lost_in_peril.Lost_In_Peril;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class MainMenuScreen implements Screen{

	private static final int EXIT_BUTTON_WIDTH = 300 *(Lost_In_Peril.WIDTH / 1163);
	private static final int EXIT_BUTTON_HEIGHT = 150 *(Lost_In_Peril.HEIGHT / 720);
	private static final int PLAY_BUTTON_WIDTH = 330 *(Lost_In_Peril.WIDTH / 1163);
	private static final int PLAY_BUTTON_HEIGHT = 150 *(Lost_In_Peril.HEIGHT / 720);
	private static final int SCORE_BUTTON_WIDTH = 270 *(Lost_In_Peril.WIDTH / 1163);
	private static final int SCORE_BUTTON_HEIGHT = 90 *(Lost_In_Peril.HEIGHT / 720);
	private static final int SETTINGS_BUTTON_WIDTH = 100 *(Lost_In_Peril.WIDTH / 1163);
	private static final int SETTINGS_BUTTON_HEIGHT = (100* (Lost_In_Peril.HEIGHT / 720));
	private static int EXIT_BUTTON_X = 250;
	private static int EXIT_BUTTON_Y = 75;
	private static int PLAY_BUTTON_Y = 350;
	private static int PLAY_BUTTON_X = 300;
	private static int SCORE_BUTTON_X = 250;
	private static int SCORE_BUTTON_Y = 220;
	private static int SETTINGS_BUTTON_X = 400;
	private static int SETTINGS_BUTTON_Y = 50;
	public static float musicVolume = .5f;
	public static boolean godMode = false;
	public static boolean showSettings = false;
	public static boolean showScoreScreen = false;

	Lost_In_Peril game;
	private OrthographicCamera cam;
	private Viewport viewport;
	Texture exitButtonActive;
	Texture exitButtonInactive;
	Texture playButtonActive;
	Texture playButtonInactive;
	Texture scoreButton;
	Texture settingsButton;
	Texture mainBackground;
	public static Controller pad;
	private settingsWindow settings;
	private ScoreScreen scoreWindow;
	BitmapFont font;
	Vector2 touchLogic;
	InputMultiplexer inputMultiplexer;
	RayHandler rayHandler;
	World world;
	PointLight light;
	private float DISTANCE;
	int timeCount;


	//Sound mainMenuScreenSound = Gdx.audio.newSound(Gdx.files.internal("PM_AR_125_Fm_A.ogg"));	//only ogg works from zip https://www.omgubuntu.co.uk/2017/05/simple-sound-converter-ubuntu
	Music menuMusic;


	public MainMenuScreen(Lost_In_Peril game) {
		font = new BitmapFont();
		this.game = game;
		cam = new OrthographicCamera();
		viewport = new FitViewport(Lost_In_Peril.WIDTH, Lost_In_Peril.HEIGHT, cam);
		mainBackground = new Texture("mainBackground1.png");
		playButtonActive = new Texture("play_button_active.png");
		playButtonInactive = new Texture("play_button_inactive.png");
		exitButtonActive = new Texture("exit_button_active.png");
		exitButtonInactive = new Texture("exit_button_inactive.png");
		scoreButton = new Texture("score.png");
		settingsButton= new Texture("settingsButton.png");
		System.out.println("Platform: " + Lost_In_Peril.platformName);
		menuMusic= Gdx.audio.newMusic(Gdx.files.internal("PM_AR_125_Fm_A.ogg"));
		musicVolume = .5f;
		menuMusic.setLooping(true); //now plays in loop (delay)
		menuMusic.setVolume(musicVolume);
		menuMusic.play();

		settings = new settingsWindow(game.batch);
		scoreWindow = new ScoreScreen(game.batch);
		touchLogic = new Vector2(0,0);
		inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(settings.stage);
		inputMultiplexer.addProcessor(scoreWindow.scoreStage);
		Gdx.input.setInputProcessor(inputMultiplexer);

		//Controller support, only for specific Xbox controllers
		pad = null;
		for (Controller c : Controllers.getControllers()) {
			  System.out.println(c.getName());
			  if(c.getName().contains("360")) {
				  pad = c;
			  }
			  else if(c.getName().contains("XBOX") || c.getName().contains("Xbox")) {
			  	  System.out.println("Controller obtained.");
				  pad = c;
				  System.out.println(c);
			  }
		}

		//DRAWING IMAGES TO SCREEN
		PLAY_BUTTON_X = (Lost_In_Peril.WIDTH / 2) - (PLAY_BUTTON_WIDTH / 2);
		PLAY_BUTTON_Y = (Lost_In_Peril.HEIGHT / 2) - ((PLAY_BUTTON_HEIGHT / 3));

		EXIT_BUTTON_X = Lost_In_Peril.WIDTH / 2 - (PLAY_BUTTON_WIDTH / 2);
		EXIT_BUTTON_Y = Lost_In_Peril.HEIGHT / 5 - (PLAY_BUTTON_HEIGHT / 2);

		SCORE_BUTTON_X = (Lost_In_Peril.WIDTH / 2) - ((SCORE_BUTTON_WIDTH )); /*/ 9)*10*/
		SCORE_BUTTON_Y =  Lost_In_Peril.HEIGHT / 4 - SCORE_BUTTON_HEIGHT / 10;

		SETTINGS_BUTTON_X = (Lost_In_Peril.WIDTH / 10) * 8;
		SETTINGS_BUTTON_Y = EXIT_BUTTON_Y;

		timeCount = 0;
		DISTANCE = 400;

		//System.out.println(Gdx.graphics.getMonitor());
        System.out.println(Gdx.graphics.getDisplayMode());
        System.out.println(Gdx.graphics.getDeltaTime());
        System.out.println(Gdx.graphics.getFramesPerSecond());

        world = new World(new Vector2(0,0),false);
        rayHandler = new RayHandler(world);
        rayHandler.setCombinedMatrix(cam.combined);
        rayHandler.setShadows(false);

        light = new box2dLight.PointLight(rayHandler, 1000,new Color(.95f,.95f,.95f,.95f),100000,578413,380100);

	}

	@Override
	public void show() {

	}

	public void update(float dt) {
		settings.update(dt);
		scoreWindow.update(dt);

		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}

		timeCount += dt;
		if(DISTANCE < 50){
			if(timeCount >= .75f){
				DISTANCE-=100;
				light.setDistance(DISTANCE);
				timeCount = 0;
			}
		}
		else{
			if(timeCount >= .75f){
				DISTANCE+=100;
				light.setDistance(DISTANCE);
				timeCount = 0;
			}
		}

		/*if(Gdx.input.isTouched()){
			System.out.println(Gdx.input.getX() + "" + Gdx.input.getY());
		}
		System.out.println(light.getX());*/

		//light = new box2dLight.PointLight(rayHandler, 100,new Color(.95f,.95f,.95f,.95f),50f,Lost_In_Peril.WIDTH/2,Lost_In_Peril.HEIGHT/2);
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();

		game.batch.draw(mainBackground, 0, 0, Lost_In_Peril.WIDTH, Lost_In_Peril.HEIGHT);

		//Touch screen Start
        /*
		if (Gdx.input.isTouched()){
			/*touchLogic = new Vector2(Gdx.input.getX(),Gdx.input.getY());
			touchLogic = Lost_In_Peril.viewport.unproject(touchLogic);

			//System.out.println(touchLogic);
			//showSettings = true;
			game.setScreen(new GameScreen(game));
		}*/

		//System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
		//PLAY BUTTON:  438, 222
		//System.out.println("PlayButton: " + PLAY_BUTTON_X);

		if((!showSettings) && Gdx.input.getX() < PLAY_BUTTON_X + PLAY_BUTTON_WIDTH && Gdx.input.getX() > PLAY_BUTTON_X && Lost_In_Peril.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT &&
				Lost_In_Peril.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {

			game.batch.draw(playButtonActive,PLAY_BUTTON_X,PLAY_BUTTON_Y,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);

			if(Gdx.input.isTouched() && Gdx.input.getX() < PLAY_BUTTON_X + PLAY_BUTTON_WIDTH && Gdx.input.getX() > PLAY_BUTTON_X && Lost_In_Peril.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT &&
                    Lost_In_Peril.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y){
				game.batch.draw(playButtonActive, PLAY_BUTTON_X, PLAY_BUTTON_Y , PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
				menuMusic.stop();
				//menuMusic.dispose();
				////ggss = new GameScreen(new Lost_In_Peril());
				//this.dispose();
				game.setScreen(new GameScreen(game));
				//game.batch.end();

				//return;
				//dispose();
			}
		}
		else{
			game.batch.draw(playButtonInactive, PLAY_BUTTON_X, PLAY_BUTTON_Y , PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
		}

		/*
		//PLAY BUTTON:
		int PLAY_BUTTON_X = Lost_In_Peril.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
		if((!showSettings) && touchLogic.x < PLAY_BUTTON_X + PLAY_BUTTON_WIDTH && touchLogic.x > PLAY_BUTTON_X && Lost_In_Peril.HEIGHT - touchLogic.y < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT &&
				Lost_In_Peril.HEIGHT - touchLogic.y > PLAY_BUTTON_Y) {

			game.batch.draw(playButtonActive, PLAY_BUTTON_X, PLAY_BUTTON_Y , PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
			menuMusic.stop();
			menuMusic.dispose();
			game.setScreen(new GameScreen(game));
			this.dispose();
			dispose();
		}
		else{
			game.batch.draw(playButtonInactive, PLAY_BUTTON_X, PLAY_BUTTON_Y , PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
		}

		 */

		//Play button for android CRASHES FROM TOO MUCH INPUT
		/*
		if(Gdx.input.isTouched() && Gdx.input.getX() < PLAY_BUTTON_X + PLAY_BUTTON_WIDTH && Gdx.input.getX() > PLAY_BUTTON_X && Lost_In_Peril.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT &&
				Lost_In_Peril.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y){
			//this.dispose();
			game.setScreen(new GameScreen(game));
		}*/

		//SCORE BUTTON:

		game.batch.draw(scoreButton, SCORE_BUTTON_X, SCORE_BUTTON_Y /*, width, height, srcX, srcY, srcWidth, srcHeight*/);
		if((!showSettings) && Gdx.input.getX() < SCORE_BUTTON_X + SCORE_BUTTON_WIDTH + 151 && Gdx.input.getX() > SCORE_BUTTON_X && Lost_In_Peril.HEIGHT - Gdx.input.getY() < SCORE_BUTTON_Y + SCORE_BUTTON_HEIGHT &&
				Lost_In_Peril.HEIGHT - Gdx.input.getY() > SCORE_BUTTON_Y) {
			if(Gdx.input.isTouched()) {
				showScoreScreen = true;

			}
		}


		//EXIT BUTTON:
		//EXIT_BUTTON_X = Lost_In_Peril.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
		if((!showSettings) && Gdx.input.getX() < EXIT_BUTTON_X + EXIT_BUTTON_WIDTH && Gdx.input.getX() > EXIT_BUTTON_X && Lost_In_Peril.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT &&
				Lost_In_Peril.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
			game.batch.draw(exitButtonActive, EXIT_BUTTON_X, EXIT_BUTTON_Y , EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()) {
				//dispose();
				Gdx.app.exit();
			}
		}

		else{
			game.batch.draw(exitButtonInactive, EXIT_BUTTON_X, EXIT_BUTTON_Y , EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
		}

		//SETTINGS BUTTON:
		//SETTINGS_BUTTON_X = (Lost_In_Peril.WIDTH / 4) * 3 - (SETTINGS_BUTTON_WIDTH / 2) + 200;
		game.batch.draw(settingsButton, SETTINGS_BUTTON_X, SETTINGS_BUTTON_Y, SETTINGS_BUTTON_WIDTH, SETTINGS_BUTTON_HEIGHT); //LAST TWO VARIABLE WERE MISSING! Hard to hit sweetspot when image is so big and zone is so small!
		if(Gdx.input.getX() < SETTINGS_BUTTON_X + SETTINGS_BUTTON_WIDTH && Gdx.input.getX() > SETTINGS_BUTTON_X) {
			//System.out.println("Good!");
			if(Lost_In_Peril.HEIGHT - Gdx.input.getY() < SETTINGS_BUTTON_Y + SETTINGS_BUTTON_HEIGHT && Lost_In_Peril.HEIGHT - Gdx.input.getY() > SETTINGS_BUTTON_Y) {
				//System.out.println("GREAT!");
				if(Gdx.input.isTouched()) {
					//System.out.println("grand.");
					//menuMusic.stop();
					//game.setScreen(new SettingsScreen2(game));
					showSettings = true;


				}
			}
		}

		//game.batch.draw(font); Gdx.graphics.getDisplayMode(),10,10);

		/*
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			//pause menu
			System.out.println(SETTINGS_BUTTON_X);
		}
		if(Gdx.input.isKeyPressed(Keys.P)) {
			System.out.println(Gdx.input.getY());
		}*/

		game.batch.end();

		//PLAY WITH CONTROLLER
		if(pad != null) {
			if(pad.getButton(Xbox.START)) {
				//this.dispose();
				game.setScreen(new GameScreen(game));
			}

			//testing values of API
			if(pad.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) != 0) {
				System.out.println(pad.getButton((Xbox.L_BUMPER)));
				System.out.println(pad.getButton(Xbox.L_STICK));
				System.out.println(pad.getAxis(Xbox.R_STICK_HORIZONTAL_AXIS));
			}
		}

		rayHandler.setCombinedMatrix(cam.combined);
		rayHandler.updateAndRender();

		if(showSettings) {
			settingsWindow.stage.draw();
			menuMusic.setVolume(musicVolume);
		}

		if(showScoreScreen){
			scoreWindow.scoreStage.draw();
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
			game.setScreen(new GameScreen(game));
		}

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
		menuMusic.dispose();
		world.dispose();
		rayHandler.dispose();
		game.batch.dispose();

	}

}
