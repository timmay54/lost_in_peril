package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.controllers.mappings.Xbox;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.Sprites.Coin;
import com.freebandz.lost_in_peril.Sprites.Dragon;
import com.freebandz.lost_in_peril.Sprites.Mine;
import com.freebandz.lost_in_peril.Sprites.Teleporter;
import com.freebandz.lost_in_peril.Sprites.Torch;
import com.freebandz.lost_in_peril.Sprites.enemyFighter;
import com.freebandz.lost_in_peril.Tools.B2WorldCreator;
import com.freebandz.lost_in_peril.Tools.Controller;
import com.freebandz.lost_in_peril.Tools.WorldContactListener;
import com.freebandz.lost_in_peril.Sprites.Randy;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class GameScreen implements Screen{
	public static final float SPEED = 10;
	public static String Teleport = null;
	//Texture img;
	//Texture link;
	private TextureAtlas atlas;

	//initiate variables that are used to create objects throughout game
	public static HUD hud;
	private pauseMenu pause;
	private gameOver gameover;
	private Randy player;
	private enemyFighter boss;
	private Dragon dragon;
	private Torch torch1;
	private Torch torch2;
	private Torch torch3;
	private Torch torch4;
	private Torch torch5;
	private Torch torch6;
	private Torch torch7;
	private Torch torch8;
	private Torch torch9;
	private Torch torch10;
	private Torch torch11;
	private Torch torch12;
	private Torch torch13;
	private Torch torch14;
	private Torch torch15;
	private Torch torch16;
	private Torch torch17;
	private Torch torch18;
	private Torch torch19;
	//private Torch torch20;
	//private Torch torch21;
	private Torch torch22;
	private Torch torch23;
	private Torch torch24;
	private Torch torch25;
	private Torch torch26;
	private Mine mine1;
	private Mine mine2;
	private Mine mine3;
	private Mine mine4;
	private Mine mine5;
	private Mine mine6;
	private Mine mine7;

	//hidden room
	private Mine mine8;
	private Mine mine9;
	private Mine mine10;
	private Mine mine11;

	private Coin coin1;
	private Coin coin2;
	private Coin coin3;
	private Coin coin4;
	private Coin coin5;
	private Coin coin6;
	private Coin coin7;
	private Coin coin8;
	private Coin coin9;
	private Coin coin10;
	private Coin coin11;
	private Coin coin12;
	private Coin coin13;
	private Coin coin14;

	//hidden room
	private Coin coin15;
	private Coin coin16;
	private Coin coin17;
	private Coin coin18;
	private Coin coin19;
	private Coin coin20;
	private Coin coin21;


	float x = 863;
	float y = 859;
	public static boolean boolPause = false;
	public static boolean returnToMain = false;

	Lost_In_Peril game;

	//handlers that determine aspects such as screen and the map
	private OrthographicCamera cam;
	private Viewport gamePort;
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    public RayHandler rayHandler;
    public RayHandler rayHandler2;
    public PointLight lighttest;
    public AssetManager manager;

    //controller input
    Controller controller;
    public InputMultiplexer inputMultiplexer = new InputMultiplexer();

    //music input
	Music gameMusic = Gdx.audio.newMusic(Gdx.files.internal("PM_INFECTED_05.ogg"));

	//getters and setters
	public TiledMap getMap() {
		return map;
	}
	public World getWorld() {
		return world;
	}
	public TextureAtlas getAtlas(){
		return atlas;
	}
	public RayHandler getRayHandler(){
		return rayHandler;
	}
	public Game getGame(){
		return game;
	}
	public AssetManager getManager(){
		return manager;
	}

	float grn = 0f;

	//actual game screen
	public GameScreen(Lost_In_Peril game) {
		this.game = game;
		atlas = new TextureAtlas(Gdx.files.internal("SpaceAssets.atlas"));
		boolPause = false;
		// = false;
		gameMusic.setLooping(true);
		gameMusic.setVolume(MainMenuScreen.musicVolume);
		gameMusic.play();

		//random coins like chests
		//negative points
		//

		manager = new AssetManager();
		//manager.load("PM_HD_Designed_03_Doors_Old_Wooden_Scary_Horror_Open_Creeking.wav", Sound.class);
		manager.finishLoading();


		cam = new OrthographicCamera();
		gamePort = new FitViewport(Lost_In_Peril.WIDTH , Lost_In_Peril.HEIGHT, cam);
		hud = new HUD(game.batch);

		controller = new Controller(game.batch);
		inputMultiplexer.addProcessor(controller.stage);

		pause = new pauseMenu(game.batch);
		inputMultiplexer.addProcessor(pause.pauseStage);

		gameover = new gameOver(game.batch);
		inputMultiplexer.addProcessor(gameover.overStage);

		mapLoader = new TmxMapLoader();
		map = mapLoader.load("MAP2_lip.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / Lost_In_Peril.PPM );

		cam.position.set(gamePort.getScreenWidth()  , gamePort.getWorldHeight() , 0 );
		//cam.setToOrtho(false, 1280, 720);	??
		cam.zoom = .4f;
		/*if(MainMenuScreen.platformName == "android"){
			cam.zoom = .4f; //.4 was norm
		}*/
		world = new World(new Vector2(0, 0), true);
		b2dr = new Box2DDebugRenderer();
		new B2WorldCreator(this);
		RayHandler.setGammaCorrection(true);
		RayHandler.useDiffuseLight(true);
		rayHandler = new RayHandler(world);
		rayHandler.setCombinedMatrix(cam.combined);
		rayHandler.setShadows(true);
		rayHandler.setAmbientLight(.01f,.01f,.01f,.01f);


		/*
		new box2dLight.PointLight(rayHandler, 1000, Color.ORANGE,100f,1580f,450f);
		new box2dLight.PointLight(rayHandler, 1000, Color.ORANGE,100f,1280f,400f);
		new box2dLight.PointLight(rayHandler, 1000, Color.ORANGE,100f,1350f,350f);
		new box2dLight.PointLight(rayHandler, 1000, Color.ORANGE,100f,1780f,450f);
		new box2dLight.PointLight(rayHandler, 1000, Color.ORANGE,100f,1580f,350f);*/

		player = new Randy(world, this, 1780, 360, rayHandler);
		boss = new enemyFighter(this, 10, 10,rayHandler);
		dragon = new Dragon(this, 10,10,rayHandler);


		//actual creation of torch, mine, and coin
		torch1 = new Torch(this, 1658, 464, rayHandler);
		torch2 = new Torch(this, 1658, 400, rayHandler);
		torch3 = new Torch(this, 1658, 337, rayHandler);
		torch4 = new Torch(this, 1753, 464, rayHandler);
		torch5 = new Torch(this, 1753, 400, rayHandler);
		torch6 = new Torch(this, 1753, 337, rayHandler);
		torch7 = new Torch(this, 1658, 750, rayHandler);
		torch8 = new Torch(this, 1785, 750, rayHandler);
		torch9 = new Torch(this, 1913, 750, rayHandler);
		torch10 = new Torch(this, 1017, 750, rayHandler);
		torch11 = new Torch(this, 1305, 750, rayHandler);
		torch12 = new Torch(this, 635, 750, rayHandler);
		torch13 = new Torch(this, 890, 940, rayHandler);
		torch14 = new Torch(this, 761, 940, rayHandler);
		torch15 = new Torch(this, 1146, 591, rayHandler);
		torch16 = new Torch(this, 954, 591, rayHandler);
		torch17 = new Torch(this, 698, 591, rayHandler);
		torch18 = new Torch(this, 1370, 528, rayHandler);
		torch19 = new Torch(this, 1370, 400, rayHandler);
		//torch20 = new Torch(this, 1082, 367, rayHandler);
		//torch21 = new Torch(this, 1273, 207, rayHandler);
		torch22 = new Torch(this, 1562, 464, rayHandler);
		torch23 = new Torch(this, 1562, 845, rayHandler);
		torch24 = new Torch(this, 1691, 1103, rayHandler);
		torch25 = new Torch(this, 1435, 1103, rayHandler);
		torch26 = new Torch(this, 1370, 974, rayHandler);
		mine1 = new Mine(this, 1775, 420);
		mine2 = new Mine(this, 1200, 700);
		mine3 = new Mine(this, 1605, 1105);
		mine4 = new Mine(this, 1530, 600);
		mine5 = new Mine(this, 1932, 710);
		mine6 = new Mine(this, 2800, 660);
		mine7 = new Mine(this, 2800, 750);

		//hidden room
		mine8 = new Mine(this, 850, 2000);
		mine9 = new Mine(this, 860, 2065);
		mine10 = new Mine(this, 905, 2065);
		mine11 = new Mine(this, 915, 2000);

		coin1 = new Coin(this, 1350, 725);
		coin2 = new Coin(this, 1530, 450);
		coin3 = new Coin(this, 1200, 650);
		coin4 = new Coin(this, 855, 1055);
		coin5 = new Coin(this, 905, 1055);
		coin6 = new Coin(this, 1470, 1100);
		coin7 = new Coin(this, 1000, 670);
		coin8 = new Coin(this, 1950, 400);
		coin9 = new Coin(this, 1800, 700);
		coin10 = new Coin(this, 3300, 250);
		coin11 = new Coin(this, 3100, 720);
		coin12 = new Coin(this, 2400, 695);
		coin13 = new Coin(this, 2200, 660);
		coin14 = new Coin(this, 2750, 730);

		//hidden room
		coin15 = new Coin(this, 1000, 2400);
		coin16 = new Coin(this, 1000, 2300);
		coin17 = new Coin(this, 900, 2400);
		coin18 = new Coin(this, 900, 2300);
		coin19 = new Coin(this, 800, 2400);
		coin20 = new Coin(this, 800, 2300);
		coin21 = new Coin(this, 900, 2350);


		Gdx.input.setInputProcessor(inputMultiplexer);
		world.setContactListener(new WorldContactListener());

		dragon.b2body.setLinearVelocity( SPEED, SPEED * .85f);

	}

	public void handleInput(float dt) {
		/*if(Gdx.input.isTouched()) {
			//System.out.println(player.getX() + " " + player.getY());
			//System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
			//controller.isUpPressed();
		}*/

		//ANDROID SPECIFIC CONTROLS
			//controller.resize(Lost_In_Peril.WIDTH, Lost_In_Peril.HEIGHT);
		if(controller.isUpPressed()){
			player.b2body.applyLinearImpulse(new Vector2(0,(SPEED)), player.b2body.getWorldCenter(), true);

		}

		if(controller.isDownPressed()){
			player.b2body.applyLinearImpulse(new Vector2(0,-(SPEED)), player.b2body.getWorldCenter(), true);
		}

		if(controller.isRightPressed()){
			player.b2body.applyLinearImpulse(new Vector2(SPEED,0), player.b2body.getWorldCenter(), true);
		}

		if(controller.isLeftPressed()){
			player.b2body.applyLinearImpulse(new Vector2(-SPEED,0), player.b2body.getWorldCenter(), true);
		}



		if((Gdx.input.isKeyPressed(Keys.UP)) || (Gdx.input.isKeyPressed(Keys.W)) || (MainMenuScreen.pad !=null && MainMenuScreen.pad.getButton(Xbox.Y))) {
			y+=SPEED * Gdx.graphics.getDeltaTime();
			player.b2body.applyLinearImpulse(new Vector2(0,(SPEED)), player.b2body.getWorldCenter(), true);
		}
		if((Gdx.input.isKeyPressed(Keys.DOWN) || (Gdx.input.isKeyPressed(Keys.S))) || (MainMenuScreen.pad !=null && MainMenuScreen.pad.getButton(Xbox.A))) {
			y-=SPEED * Gdx.graphics.getDeltaTime();
			player.b2body.applyLinearImpulse(new Vector2(0,-(SPEED)), player.b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT) || (Gdx.input.isKeyPressed(Keys.D)) || (MainMenuScreen.pad != null && MainMenuScreen.pad.getButton(Xbox.B))) {
			x+=SPEED * Gdx.graphics.getDeltaTime();
			player.b2body.applyLinearImpulse(new Vector2(SPEED,0), player.b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT) || (Gdx.input.isKeyPressed(Keys.A)) || (MainMenuScreen.pad!=null && MainMenuScreen.pad.getButton(Xbox.X))) {
			x-=SPEED * Gdx.graphics.getDeltaTime();
			player.b2body.applyLinearImpulse(new Vector2(-SPEED,0), player.b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			//pause menu
			boolPause = true;
		}
		if(MainMenuScreen.godMode) {
			if (Gdx.input.isKeyPressed(Keys.Q)) {
				cam.rotate(-2, 0, 0, 1);
			}
			if (Gdx.input.isKeyPressed(Keys.E)) {
				cam.rotate(2, 0, 0, 1);
			}
			if (Gdx.input.isKeyPressed(Keys.I)) {
				cam.zoom -= 0.02;
				System.out.println(cam.zoom);
			}
			if (Gdx.input.isKeyPressed(Keys.O)) {
				cam.zoom += 0.02;
			}
		}

		//For Controller Support
		if(MainMenuScreen.pad != null) {
			if(MainMenuScreen.pad.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) < -0.2  ||
			   MainMenuScreen.pad.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS) > 0.2) {
			      player.b2body.applyLinearImpulse(new Vector2(MainMenuScreen.pad.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS)*SPEED,0), player.b2body.getWorldCenter(), true);
			      //player.defineRandy(); Funny lol
			}

			if(MainMenuScreen.pad.getAxis(Xbox.L_STICK_VERTICAL_AXIS) < -0.2  ||
			   MainMenuScreen.pad.getAxis(Xbox.L_STICK_VERTICAL_AXIS) > 0.2) {
			      //System.out.println(player.getX());
			      player.b2body.applyLinearImpulse(new Vector2(0,MainMenuScreen.pad.getAxis(Xbox.L_STICK_VERTICAL_AXIS)*-SPEED), player.b2body.getWorldCenter(), true);
			      //player.b2body.setTransform(player.getX()+(SPEED * MainMenuScreen.pad.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS)), y, 0);
			      //player.defineRandy();
			}
		}

		//System.out.println(player.b2body.getLinearVelocity().x != 0f);
		//System.out.println(player.b2body.getPosition().x + " , " + player.b2body.getPosition().y );
		//System.out.println(player.b2body.getLinearVelocity());

		/*if(player.getX() < 350) {
			player.setCenterX(450);
			player.setCenterY(3200);
		}*/

		//If no button press, decrement speed to stop Randy
		if (player.b2body.getLinearVelocity().x != 0){
			player.b2body.setLinearVelocity(.9f * player.b2body.getLinearVelocity().x, player.b2body.getLinearVelocity().y);
			if(player.b2body.getLinearVelocity().isZero(.1f)) {
				player.b2body.setLinearVelocity(0f, player.b2body.getLinearVelocity().y);
			}
		}
		if (player.b2body.getLinearVelocity().y != 0){
			player.b2body.setLinearVelocity(player.b2body.getLinearVelocity().x, .9f * player.b2body.getLinearVelocity().y);
			if(player.b2body.getLinearVelocity().isZero(.1f)) {
				player.b2body.setLinearVelocity(player.b2body.getLinearVelocity().x, 0f );
			}
		}

		boss.b2body.setLinearVelocity(boss.b2body.getLinearVelocity().x * .85f, boss.b2body.getLinearVelocity().y * .85f);


	}

	public void update(float dt) {
		//handleInput(dt);
		//System.out.println("boolPause: " + boolPause);
        //System.out.println(Gdx.graphics.getFramesPerSecond());
		Vector2 tempPlayerVector = new Vector2(0,0);
		if(!boolPause) {
			handleInput(dt);
			hud.update(dt);

		}
		else {
			tempPlayerVector = player.b2body.getLinearVelocity();
			player.b2body.setLinearVelocity(0,0);

			if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				player.b2body.setLinearVelocity(tempPlayerVector);
				boolPause = false;
			}
		}
		//System.out.println(Gdx.graphics.getFramesPerSecond());
		/*if(returnToMain == true){
			this.dispose();
			game.setScreen(new MainMenuScreen(game));
		}*/


		//System.out.println(boss.b2body.getPosition().x + " " + boss.b2body.getPosition().y);
		cam.position.x = player.b2body.getPosition().x;
		cam.position.y = player.b2body.getPosition().y;

	  //world.step(1/60f, 6, 100); / NORMAL
		//world.step(1f, 6, 100);

		cam.update();
		renderer.setView(cam);

		if (player.b2body.getPosition().x > 1991 && player.b2body.getPosition().x < 2490 && player.b2body.getPosition().y > 1080 && player.b2body.getPosition().y < 1096) {
			hud.setScore(hud.getScore() + 1);
			//hud.worldTimer++;

		}

		/*
		if(Teleport != null){
			player.b2body.setTransform(2000,600,player.b2body.getAngle());
			//player.b2body.setActive(true);
			player.b2body.setAwake(true);
            player.b2body.resetMassData();
            System.out.println(player.b2body.getWorld().isLocked());
			player.b2body.setType(BodyDef.BodyType.DynamicBody);
			player.b2body.setActive(true);
            //world.step(1f, 6, 100);
			//player.b2body.applyLinearImpulse(-3,3,player.b2body.getLocalCenter().x,player.b2body.getLocalCenter().y,true);

			//player = new Randy(world,this);
			world.destroyBody(player.b2body);
			player = new Randy(world,this, 1775,360);
			player.b2body.setActive(true);
			player.b2body.setAwake(true);
		}*/

		boss.update(dt); //for sprite image location on screen
		dragon.update(dt);
		player.update(dt);
		gameover.update(dt);
		pause.update(dt);

		torch1.update(dt);
		torch2.update(dt);
		torch3.update(dt);
		torch4.update(dt);
		torch5.update(dt);
		torch6.update(dt);
		torch7.update(dt);
		torch8.update(dt);
		torch9.update(dt);
		torch10.update(dt);
		torch11.update(dt);
		torch12.update(dt);
		torch13.update(dt);
		torch14.update(dt);
		torch15.update(dt);
		torch16.update(dt);
		torch17.update(dt);
		torch18.update(dt);
		torch19.update(dt);
		//torch20.update(dt);
		//torch21.update(dt);
		torch22.update(dt);
		torch23.update(dt);
		torch24.update(dt);
		torch25.update(dt);
		torch26.update(dt);
		mine1.update(dt);
		mine2.update(dt);
		mine3.update(dt);
		mine4.update(dt);
		mine5.update(dt);
		mine6.update(dt);
		mine7.update(dt);

		//hidden room
		mine8.update(dt);
		mine9.update(dt);
		mine10.update(dt);
		mine11.update(dt);

		coin1.update(dt);
		coin2.update(dt);
		coin3.update(dt);
		coin4.update(dt);
		coin5.update(dt);
		coin6.update(dt);
		coin7.update(dt);
		coin8.update(dt);
		coin9.update(dt);
		coin10.update(dt);
		coin11.update(dt);
		coin12.update(dt);
		coin13.update(dt);
		coin14.update(dt);

		//hidden room
		coin15.update(dt);
		coin16.update(dt);
		coin17.update(dt);
		coin18.update(dt);
		coin19.update(dt);
		coin20.update(dt);
		coin21.update(dt);

		world.step(1f,6,100);


	}


	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		//System.out.println(game.getScreen());
		/*
		if(game.getScreen() == new GameScreen(game)){
			return;
		}*/

		if (returnToMain == true){
			returnToMain = false;
			boolPause = false;

			dispose();
			game.setScreen(new MainMenuScreen(game));
			return;
		}

		update(delta);

		game.batch.setProjectionMatrix(hud.stageHud.getCamera().combined);

		//System.out.println(Gdx.graphics.getFramesPerSecond());

		if(MainMenuScreen.godMode){
		    grn = (float)(Math.random());
        }
		else{
			grn = 0f;
		}

		Gdx.gl.glClearColor(.3f, grn, .3f, .2f );
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.render();
		if(MainMenuScreen.godMode){
			b2dr.render(world, cam.combined);
		}

		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();

		//game.batch.draw(link, this.gamePort.getScreenWidth()/2 - 45, this.gamePort.getScreenHeight()/2 - 45,45/Lost_In_Peril.PPM,50/Lost_In_Peril.PPM);
		//game.batch.draw(cob, Lost_In_Peril.WIDTH * boss.b2body.getPosition().x, boss.b2body.getPosition().y / Lost_In_Peril.HEIGHT, 45, 45);

		//draw torch, mine, player, boss, and coins
		player.draw(game.batch);
		boss.draw(game.batch);
		dragon.draw(game.batch);
		torch1.draw(game.batch);
		torch2.draw(game.batch);
		torch3.draw(game.batch);
		torch4.draw(game.batch);
		torch5.draw(game.batch);
		torch6.draw(game.batch);
		torch7.draw(game.batch);
		torch8.draw(game.batch);
		torch9.draw(game.batch);
		torch10.draw(game.batch);
		torch11.draw(game.batch);
		torch12.draw(game.batch);
		torch13.draw(game.batch);
		torch14.draw(game.batch);
		torch15.draw(game.batch);
		torch16.draw(game.batch);
		torch17.draw(game.batch);
		torch18.draw(game.batch);
		torch19.draw(game.batch);
		//torch20.draw(game.batch);
		//torch21.draw(game.batch);
		torch22.draw(game.batch);
		torch23.draw(game.batch);
		torch24.draw(game.batch);
		torch25.draw(game.batch);
		torch26.draw(game.batch);
		mine1.draw(game.batch);
		mine2.draw(game.batch);
		mine3.draw(game.batch);
		mine4.draw(game.batch);
		mine5.draw(game.batch);
		mine6.draw(game.batch);
		mine7.draw(game.batch);

		//hidden room
		mine8.draw(game.batch);
		mine9.draw(game.batch);
		mine10.draw(game.batch);
		mine11.draw(game.batch);

		coin1.draw(game.batch);
		coin2.draw(game.batch);
		coin3.draw(game.batch);
		coin4.draw(game.batch);
		coin5.draw(game.batch);
		coin6.draw(game.batch);
		coin7.draw(game.batch);
		coin8.draw(game.batch);
		coin9.draw(game.batch);
		coin10.draw(game.batch);
		coin11.draw(game.batch);
		coin12.draw(game.batch);
		coin13.draw(game.batch);
		coin14.draw(game.batch);

		//hidden room
		coin15.draw(game.batch);
		coin16.draw(game.batch);
		coin17.draw(game.batch);
		coin18.draw(game.batch);
		coin19.draw(game.batch);
		coin20.draw(game.batch);
		coin21.draw(game.batch);




		game.batch.end();

		rayHandler.setCombinedMatrix(cam);
		rayHandler.updateAndRender();

		hud.stageHud.draw();
		if(boolPause) {
			if(hud.worldTimer <= 0){
				gameover.overStage.draw();
				/*
				if(gameOver.overStage.touchUp()){
					game.setScreen(new MainMenuScreen(game));
				}

				 */
			}
			else {
				pause.pauseStage.draw();
			}
		}

		if(Lost_In_Peril.platformName.equals("android")) {
			controller.draw();
		}
		if(Gdx.app.getType() == Application.ApplicationType.Android){
			controller.draw();
		}

	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
		if(Lost_In_Peril.platformName.equals("android")) {
			controller.resize(width, height);
		}
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

		//img.dispose();
		//renderer.dispose();
		System.out.println("Disposing map... ");
		map.dispose();
		System.out.println("Disposing world... ");
		world.dispose();
		System.out.println("Disposing b2dr... ");
		b2dr.dispose();
		System.out.println("Disposing hud... ");
		hud.dispose();
		System.out.println("Disposing gameMusic... ");
		gameMusic.dispose();
		System.out.println("Disposing game.batch... ");
		game.batch.dispose();
		rayHandler.dispose();

	}

}
