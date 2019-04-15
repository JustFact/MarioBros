package com.jkp.mariobros.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jkp.mariobros.MarioBros;

public class Controller {
Viewport viewport;
public Stage stage;
boolean upPressed,leftPressed, rightPressed;
OrthographicCamera cam;

public Controller(){
    cam = new OrthographicCamera();
    viewport = new FitViewport(800,480,cam);
    stage = new Stage(viewport, MarioBros.batch);
    Gdx.input.setInputProcessor(stage);

    Table table = new Table();
    table.left().bottom();

    int scale = 50;

    Image upImg = new Image(new Texture("Up.png"));
    upImg.setWidth(scale);
    upImg.setHeight(scale);
    upImg.addListener(new InputListener(){

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            upPressed = true;
            return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            upPressed = false;
        }
    });

    Image leftImg = new Image(new Texture("Left.png"));
    leftImg.setWidth(scale);
    leftImg.setHeight(scale);
    leftImg.addListener(new InputListener(){

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            leftPressed = true;
            return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            leftPressed = false;
        }
    });

    Image rightImg = new Image(new Texture("Right.png"));
    rightImg.setWidth(scale);
    rightImg.setHeight(scale);
    rightImg.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            rightPressed = true;
            return true;
        }
        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            rightPressed = false;
        }
    });

    table.add();
    table.add(upImg).size(upImg.getWidth(), upImg.getHeight());
    table.add();
    table.row().pad(5, 5, 5, 5);
    table.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight());
    table.add();
    table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight());
    table.row().padBottom(5);
    table.add();
    table.add().size(leftImg.getWidth(), leftImg.getHeight());
    table.add();

    stage.addActor(table);
}

public void draw(){
    stage.draw();
}

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void resize(int width, int height){
    viewport.update(width,height);
    }
}
