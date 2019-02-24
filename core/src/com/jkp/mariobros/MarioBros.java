package com.jkp.mariobros;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jkp.mariobros.Screens.PlayScreen;

public class MarioBros extends Game {
	public static final int V_WIDTH = 384;//500;	//1280
	public static final int V_HEIGHT = 216;//300;	//720
	public static final float PPM = 100;
	public SpriteBatch batch;	//Act as container for images/textures etc. Whole game will only have 1 SpriteBatch for memory efficiency.
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this)); //setting game's screens
	}

	@Override
	public void render () {
		super.render(); //delegate the render method to the PlayScreen screen currently active in game.
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
