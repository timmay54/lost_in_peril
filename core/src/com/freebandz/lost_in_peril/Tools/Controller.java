/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen of Western Illinois University
 */

package com.freebandz.lost_in_peril.Tools;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.screens.GameScreen;
import com.freebandz.lost_in_peril.screens.MainMenuScreen;

public class Controller {
    Viewport viewport;
    public Stage stage;
    boolean upPressed, downPressed, leftPressed, rightPressed;
    OrthographicCamera cam;
    private int btnSize = 100 * (Lost_In_Peril.WIDTH / 1162);

    public Controller(SpriteBatch ssb){
        cam = new OrthographicCamera();
        viewport = new FitViewport(Lost_In_Peril.WIDTH, Lost_In_Peril.HEIGHT,cam);
        //viewport = new FitViewport(800,480);
        stage = new Stage(viewport, ssb);

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.left().bottom();

        Image upImg = new Image(new Texture("images/up.png"));
        upImg.setSize(btnSize, btnSize);
        upImg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = true;
                return true;
                //return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = false;
                //super.touchUp(event, x, y, pointer, button);
            }
        });

        //DOWN BUTTON
        Image downImg = new Image(new Texture("images/down.png"));
        downImg.setSize(btnSize, btnSize);
        downImg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                downPressed = true;
                return true;
                //return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                downPressed = false;
                //super.touchUp(event, x, y, pointer, button);
            }
        });

        //LEFT BUTTON
        Image leftImg = new Image(new Texture("images/left.png"));
        leftImg.setSize(btnSize, btnSize);
        leftImg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                return true;
                //return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
                //super.touchUp(event, x, y, pointer, button);
            }
        });

        //RIGHT BUTTON
        Image rightImg = new Image(new Texture("images/right.png"));
        rightImg.setSize(btnSize, btnSize);
        rightImg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                return true;
                //return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
                //super.touchUp(event, x, y, pointer, button);
            }
        });

        //Insert into table
        table.add();
        table.add(upImg).size(upImg.getWidth(), upImg.getHeight());
        table.add();
        table.row().pad(5,5,5,5);
        table.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight());
        table.add();
        table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight());
        table.row().padBottom(5);
        table.add();
        table.add(downImg).size(downImg.getWidth(), downImg.getHeight());
        table.add();

        stage.addActor(table);
    }

    public void draw(){
        stage.draw();
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void resize(int width, int height){
        viewport.update(width, height);
    }
}
