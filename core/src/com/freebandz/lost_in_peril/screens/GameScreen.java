package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.controllers.mappings.Xbox;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.Sprites.enemyFighter;
import com.freebandz.lost_in_peril.Tools.B2WorldCreator;
import com.freebandz.lost_in_peril.Tools.Controller;
import com.freebandz.lost_in_peril.Tools.WorldContactListener;
import com.freebandz.lost_in_peril.Sprites.Randy;

public class GameScreen implements Screen{
	public static final float SPEED = 10;
	Texture img;
	Texture link;
	private TextureAtlas atlas;

	private HUD hud;
	private pauseMenu pause;
	private Randy player;
	private enemyFighter boss;
	float x = 863;
	float y = 859;
	public static boolean boolPause = false;

	Lost_In_Peril game;

	private OrthographicCamera cam;
	private Viewport gamePort;
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    Controller controller;

	Music gameMusic = Gdx.audio.newMusic(Gdx.files.internal("PM_INFECTED_05.ogg"));

	public TiledMap getMap() {
		return map;
	}
	public World getWorld() {
		return world;
	}
	public TextureAtlas getAtlas(){
		return atlas;
	}

	public GameScreen(Lost_In_Peril game) {
		this.game = game;
		//atlas = new TextureAtlas(Gdx.files.internal("PossibleAssets/Pack01/Original (16x16)/Sprites.png"));

		gameMusic.setLooping(true);
		gameMusic.setVolume(.58f);
		gameMusic.play();

		link = new Texture("link-sprite-png-6.gif");

		cam = new OrthographicCamera();
		gamePort = new FitViewport(Lost_In_Peril.WIDTH , Lost_In_Peril.HEIGHT, cam);
		hud = new HUD(game.batch);
		if(MainMenuScreen.platformName.equals("android")) {
			controller = new Controller(game.batch);
		}
		pause = new pauseMenu(game.batch);
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

		player = new Randy(world, this);
		boss = new enemyFighter(this, 10, 10);
		world.setContactListener(new WorldContactListener());

	}

	public void handleInput(float dt) {
		if(Gdx.input.isTouched()) {
			//System.out.println(player.getX() + " " + player.getY());
		}

		//ANDROID SPECIFIC CONTROLS
		if(MainMenuScreen.platformName.equals("android")){
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
		if(Gdx.input.isKeyPressed(Keys.Q)){
			cam.rotate(-2,0,0,1);
		}
		if(Gdx.input.isKeyPressed(Keys.E)){
			cam.rotate(2,0,0,1);
		}
		if(Gdx.input.isKeyPressed(Keys.I)){
			cam.zoom -= 0.02;
			System.out.println(cam.zoom);
		}
		if(Gdx.input.isKeyPressed(Keys.O)){
			cam.zoom += 0.02;
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
		if(!boolPause) {
			handleInput(dt);
		}
		else {

			if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				boolPause = false;
			}
		}


		//System.out.println(boss.b2body.getPosition().x + " " + boss.b2body.getPosition().y);
		cam.position.x = player.b2body.getPosition().x;
		cam.position.y = player.b2body.getPosition().y;

	  //world.step(1/60f, 6, 100); / NORMAL
		world.step(1f, 6, 2);

		cam.update();
		renderer.setView(cam);

		if (player.b2body.getPosition().x > 1991 && player.b2body.getPosition().x < 2490 && player.b2body.getPosition().y > 1080 && player.b2body.getPosition().y < 1096) {
			//hud.setScore(hud.getScore() + 1);
			//hud.worldTimer++;

		}
		hud.update(dt);

		//boss.update(dt); //for sprite image location on screen
		player.update(dt);

		pause.update(dt);
	}


	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		update(delta);
		game.batch.setProjectionMatrix(hud.stageHud.getCamera().combined);

		Gdx.gl.glClearColor(.5f, 0f, .5f, 1 );
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



		renderer.render();
		b2dr.render(world, cam.combined); //TODO Comment this to hide wall lines and circle


		hud.stageHud.draw();
		if(MainMenuScreen.platformName.equals("android")) {
			controller.draw();
		}
		if(boolPause) {
			pause.pauseStage.draw();
		}

		game.batch.begin();

		game.batch.draw(link, this.gamePort.getScreenWidth()/2 - 45, this.gamePort.getScreenHeight()/2 - 45,45/Lost_In_Peril.PPM,50/Lost_In_Peril.PPM);
		//game.batch.draw(cob, Lost_In_Peril.WIDTH * boss.b2body.getPosition().x, boss.b2body.getPosition().y / Lost_In_Peril.HEIGHT, 45, 45);
		//player.draw(game.batch);
		game.batch.end();


	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
		if(MainMenuScreen.platformName.equals("android")) {
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
		game.batch.dispose();
		img.dispose();
		renderer.dispose();
		map.dispose();
		gameMusic.dispose();
	}

}
