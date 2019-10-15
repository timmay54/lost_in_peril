package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.freebandz.lost_in_peril.Randy;

public class GameScreen implements Screen{
	public static final float SPEED = 200;
	Texture img;
	Texture gameScreenBackground;
	Texture link;
	
	private HUD hud;
	private Randy player;
	float x = 863;
	float y = 859;

	Lost_In_Peril game;
	
	private OrthographicCamera cam;
	private Viewport gamePort;
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	//Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    //private B2WorldCreator creator;
	
	Sound gameScreenSound = Gdx.audio.newSound(Gdx.files.internal("PM_INFECTED_05.ogg"));
	
	public GameScreen(Lost_In_Peril game) {
		// TODO Auto-generated constructor stub
		this.game = game;
		long id = gameScreenSound.play(1.0f);
		
		gameScreenSound.setLooping(id,true);
		
		cam = new OrthographicCamera();
		gamePort = new FitViewport(Lost_In_Peril.WIDTH / Lost_In_Peril.PPM, Lost_In_Peril.HEIGHT / Lost_In_Peril.PPM, cam);
		hud = new HUD(game.batch);
		mapLoader = new TmxMapLoader();
		map = mapLoader.load("MAP2_lip.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / Lost_In_Peril.PPM );
		
		cam.position.set(0, 0, 0);       //gamePort.getScreenWidth() / 2 , gamePort.getWorldHeight() / 2, 0 
		cam.setToOrtho(false, 1280, 720);		
		
		world = new World(new Vector2(0, 0), true);
		b2dr = new Box2DDebugRenderer(); 
		
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		player = new Randy(world);
		
		for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject)object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Lost_In_Peril.PPM, (rect.getHeight() / 2) / Lost_In_Peril.PPM);
			body = world.createBody(bdef);
			shape.setAsBox((rect.getWidth()/2 ) / Lost_In_Peril.PPM, (rect.getHeight()/2 ) / Lost_In_Peril.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		
	}
	
	public void handleInput(float dt) {
		if(Gdx.input.isTouched()) {
			System.out.println(x + " " + y);
		}
		/*cam.position.x = x;
		cam.position.y = y;*/
		
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			y+=SPEED * Gdx.graphics.getDeltaTime();
			player.b2body.applyLinearImpulse(new Vector2(0,4f), player.b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			y-=SPEED * Gdx.graphics.getDeltaTime();
			player.b2body.applyLinearImpulse(new Vector2(0,-4f), player.b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x+=SPEED * Gdx.graphics.getDeltaTime();
			player.b2body.applyLinearImpulse(new Vector2(5f,0), player.b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x-=SPEED * Gdx.graphics.getDeltaTime();
			player.b2body.applyLinearImpulse(new Vector2(-5f,0), player.b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			//pause menu
			//System.out.println("Sterling."); 
		}
	}
	
	public void update(float dt) {
		handleInput(dt);
		
		cam.position.x = player.b2body.getPosition().x;
		cam.position.y = player.b2body.getPosition().y;
		
		world.step(1/60f, 6, 2);
		
		cam.update();
		renderer.setView(cam);
	}
	
	
	@Override
	public void show() {
		link = new Texture("link-sprite-png-6.gif");
	}

	@Override
	public void render(float delta) {
		
		update(delta);
		
		game.batch.setProjectionMatrix(hud.stageHud.getCamera().combined);
		
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		b2dr.render(world, cam.combined);
		game.batch.begin();
		
		game.batch.draw(link, Lost_In_Peril.WIDTH/2, Lost_In_Peril.HEIGHT/2,45,50);
		
		game.batch.end();
		hud.stageHud.draw();
		
		//renderer.render(); COOL SHIIITTTT
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
		game.batch.dispose();
		img.dispose();
		
	}

}
