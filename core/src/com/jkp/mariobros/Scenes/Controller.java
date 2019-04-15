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
boolean upPressed2,leftPressed2, rightPressed2;
OrthographicCamera cam;

public Controller(){
    cam = new OrthographicCamera();
    viewport = new FitViewport(800,480,cam);
    stage = new Stage(viewport, MarioBros.batch);
    Gdx.input.setInputProcessor(stage);

    Table Ptable = new Table();
    Ptable.bottom();
    Ptable.setFillParent(true);

    Table table = new Table();
    table.left().bottom();

    Table table2 = new Table();
    table2.right().bottom();

    int scale = 60;


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

    //========================


    Image upImg2 = new Image(new Texture("Up.png"));
    upImg2.setWidth(scale);
    upImg2.setHeight(scale);
    upImg2.addListener(new InputListener(){

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            upPressed2 = true;
            return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            upPressed2 = false;
        }
    });

    Image leftImg2 = new Image(new Texture("Left.png"));
    leftImg2.setWidth(scale);
    leftImg2.setHeight(scale);
    leftImg2.addListener(new InputListener(){

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            leftPressed2 = true;
            return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            leftPressed2 = false;
        }
    });

    Image rightImg2 = new Image(new Texture("Right.png"));
    rightImg2.setWidth(scale);
    rightImg2.setHeight(scale);
    rightImg2.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            rightPressed2 = true;
            return true;
        }
        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            rightPressed2 = false;
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

    table2.add();
    table2.add(upImg2).size(upImg2.getWidth(), upImg2.getHeight());
    table2.add();
    table2.row().pad(5, 5, 5, 5);
    table2.add(leftImg2).size(leftImg2.getWidth(), leftImg2.getHeight());
    table2.add();
    table2.add(rightImg2).size(rightImg2.getWidth(), rightImg2.getHeight());
    table2.row().padBottom(5);
    table2.add();
    table2.add().size(leftImg2.getWidth(), leftImg2.getHeight());
    table2.add();

//.padLeft(250)
    Ptable.add(table).expandX().padBottom(-70);
    Ptable.add().expandX();
    Ptable.add().expandX();
    Ptable.add(table2).expandX().padBottom(-70);
    stage.addActor(Ptable);
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

    public boolean isUpPressed2() {
        return upPressed2;
    }

    public boolean isLeftPressed2() {
        return leftPressed2;
    }

    public boolean isRightPressed2() {
        return rightPressed2;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void resize(int width, int height){
    viewport.update(width,height);
    }
}
