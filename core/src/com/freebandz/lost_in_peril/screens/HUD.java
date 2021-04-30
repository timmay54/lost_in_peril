package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freebandz.lost_in_peril.Lost_In_Peril;

/**
 * 
 * @author timmay54
 *
 */ 

public class HUD implements Disposable {
	public static Stage stageHud;
	private Viewport viewportHud;
	
	public Integer worldTimer = 120;
	private float timeCount;
	private Integer score;
	
	Label countDownLabel;
	Label scoreLabel;
	
	public HUD(SpriteBatch sbb) {
		//worldTimer = 300;
		timeCount = 0;
		setScore(0);
		
		viewportHud = new StretchViewport(Lost_In_Peril.WIDTH, Lost_In_Peril.HEIGHT, new OrthographicCamera());
		stageHud = new Stage(viewportHud, sbb);
		Table tableHud = new Table();
		tableHud.top();
		tableHud.setFillParent(true);
		
		countDownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		scoreLabel = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		tableHud.add(countDownLabel).expandX().padTop(10);
		tableHud.add(scoreLabel).expandX().pad(10, 10, 0, 0);
		tableHud.row();
		
		stageHud.addActor(tableHud);
		
		
	}
	
	public Integer getScore() {
		return score;
	}

	public int getTime(){
		return worldTimer;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	
	public void update(float dt) {
		//countDownLabel.setText(String.format("%03d", worldTimer));
		timeCount += dt;
		if(timeCount >= 2 && !GameScreen.boolPause){
			worldTimer--;
			countDownLabel.setText(String.format("%03d", worldTimer));
			timeCount = 0;
		}
		scoreLabel.setText(String.format("%03d", score));
	}

	public void addScore(int value){
		score += value;
		scoreLabel.setText(String.format("%03d", score));
	}

	public void dispose(){
		stageHud.dispose();
	}
	
}
