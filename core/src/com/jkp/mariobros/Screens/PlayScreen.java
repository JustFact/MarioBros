package com.jkp.mariobros.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jkp.mariobros.MarioBros;
import com.jkp.mariobros.Scenes.Hud;

public class PlayScreen implements Screen {//Screen represents many in-game screens(main menu, setting screen, game screen)
    private MarioBros game;
    //Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(MarioBros game){
        //A constructor because we are sending the game itself to the Screen
        this.game = game; //refreshes the game itself
        //texture = new Texture("badlogic.jpg");
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MarioBros.V_WIDTH,MarioBros.V_HEIGHT,gamecam);
        hud =  new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
            gamecam.position.x += 100*dt;
        }
    }

    public void update(float dt){
        handleInput(dt);

        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);   //clearing out color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);           // clearing the screen

        renderer.render();

//        game.batch.setProjectionMatrix(gamecam.combined);
//        game.batch.begin();
//        game.batch.draw(texture, 0,0);      //adding the texture to the batch SpriteBatch
//        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);  //refreshes the width and height of the game viewport
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

    }
}
