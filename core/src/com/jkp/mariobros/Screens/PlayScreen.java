package com.jkp.mariobros.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jkp.mariobros.Scenes.Controller;
import com.jkp.mariobros.MarioBros;
import com.jkp.mariobros.Scenes.Hud;
import com.jkp.mariobros.Sprites.Mario;
import com.jkp.mariobros.Tools.B2WorldCreator;
import com.jkp.mariobros.Tools.WorldContactListener;

public class PlayScreen implements Screen {//Screen represents many in-game screens(main menu, setting screen, game screen)
    private MarioBros game;
    //Texture texture;
    private TextureAtlas atlas;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Mario player;
    private Mario player2;

    Controller controller;

    public PlayScreen(MarioBros game){
        atlas = new TextureAtlas("Mario_and_Enemies.pack");

        //A constructor because we are sending the game itself to the Screen
        this.game = game; //refreshes the game itself

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MarioBros.V_WIDTH/MarioBros.PPM,MarioBros.V_HEIGHT/MarioBros.PPM,gamecam);

        hud =  new Hud(game.batch);


        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1/MarioBros.PPM );
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

        world = new World(new Vector2(0,-10), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);

        player = new Mario(world,this);
        player2 = new Mario(world,this);

        world.setContactListener(new WorldContactListener());
        controller = new Controller();

    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(controller.isUpPressed() && player.b2body.getLinearVelocity().y == 0)
            player.b2body.applyLinearImpulse(new Vector2(0,3.5f), player.b2body.getWorldCenter(), true);
        if(controller.isRightPressed()  && player.b2body.getLinearVelocity().x<=1)
            player.b2body.applyLinearImpulse(new Vector2(0.1f,0), player.b2body.getWorldCenter(), true);
        if(controller.isLeftPressed() && player.b2body.getLinearVelocity().x >= -1)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f,0), player.b2body.getWorldCenter(), true);



        if(controller.isUpPressed2() && player2.b2body.getLinearVelocity().y == 0)
            player2.b2body.applyLinearImpulse(new Vector2(0,3.5f), player2.b2body.getWorldCenter(), true);
        if(controller.isRightPressed2()&& player2.b2body.getLinearVelocity().x<=1)
            player2.b2body.applyLinearImpulse(new Vector2(0.1f,0), player2.b2body.getWorldCenter(), true);
        if(controller.isLeftPressed2()&& player2.b2body.getLinearVelocity().x >= -1)
            player2.b2body.applyLinearImpulse(new Vector2(-0.1f,0), player2.b2body.getWorldCenter(), true);

    }

    public void update(float dt){
        handleInput(dt);
        world.step(1/60f,6,2);
        player.update(dt);
        player2.update(dt);
        gamecam.position.x = player.b2body.getPosition().x;
        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(255,255,255,1);   //clearing out color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);           // clearing the screen
        renderer.render();

        b2dr.render(world,gamecam.combined);
//        game.batch.setProjectionMatrix(gamecam.combined);
//        game.batch.begin();
//        game.batch.draw(texture, 0,0);      //adding the texture to the batch SpriteBatch
//        game.batch.end();

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        player2.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        controller.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);  //refreshes the width and height of the game viewport
        controller.resize(width,height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
    }
}
