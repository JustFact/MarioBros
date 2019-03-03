package com.jkp.mariobros.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jkp.mariobros.MarioBros;

//creating a different layer of Viewport/Screen for hud to be rendered
//completly independent of the game world's Viewport.
public class Hud implements Disposable {
    public Stage stage;     //a container for widgets (eg. Labels)
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label marioLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        viewport = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();//to place widgets in certain position inside the stage
        table.top(); //put table on top of the stage.
        table.setFillParent(true);//set table to size of the stage

        countdownLabel  = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel      = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel       = new Label("Priyum Dua", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        levelLabel      = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel      = new Label("Kritesh Pant", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        marioLabel      = new Label("Jaspreet Singh", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
