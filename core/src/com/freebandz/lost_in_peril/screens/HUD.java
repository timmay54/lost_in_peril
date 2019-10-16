package com.freebandz.lost_in_peril.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freebandz.lost_in_peril.Lost_In_Peril;

/**
 * 
 * @author timmay54
 *
 */ 

public class HUD {
	public Stage stageHud;
	private Viewport viewportHud;
	
	public Integer worldTimer = 300;
	private float timeCount;
	private Integer score;
	
	Label countDownLabel;
	
	public HUD(SpriteBatch sbb) {
		//worldTimer = 300;
		timeCount = 0;
		setScore(500);
		
		viewportHud = new StretchViewport(Lost_In_Peril.WIDTH, Lost_In_Peril.HEIGHT, new OrthographicCamera());
		stageHud = new Stage(viewportHud, sbb);
		Table tableHud = new Table();
		tableHud.top();
		tableHud.setFillParent(true);
		
		countDownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		tableHud.add(countDownLabel).expandX().padTop(10);
		tableHud.row();
		
		stageHud.addActor(tableHud);
		
		
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	
	public void update(float dt) {
		countDownLabel.setText(String.format("%03d", worldTimer));
	}
	
}
