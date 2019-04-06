package com.freebandz.lost_in_peril;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lost_In_Peril extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	float x,y;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//place code here:
		
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			y+=4;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			y-=4;
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x+=4;
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x-=4;
		}
		
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
