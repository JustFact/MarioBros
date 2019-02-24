package com.jkp.mariobros.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jkp.mariobros.MarioBros;
import com.jkp.mariobros.Scenes.Hud;
import com.jkp.mariobros.Sprites.Mario;

public class PlayScreen implements Screen {//Screen represents many in-game screens(main menu, setting screen, game screen)
    private MarioBros game;
    //Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Mario player;

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

        world = new World(new Vector2(0,-10), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape  = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight()/2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight()/2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight()/2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight()/2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        player = new Mario(world);
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

        world.step(1/60f,6,2);

        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);   //clearing out color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);           // clearing the screen

        renderer.render();

        b2dr.render(world,gamecam.combined);
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
