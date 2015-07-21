package com.jnv.Betrayal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Betrayal extends Game {
	SpriteBatch batch;
	OrthographicCamera camera;
	public static int WIDTH = 720;
	public static int HEIGHT = 1280;
	FitViewport fitViewport;
	public static Content res;
	
	public void create () {
		camera = new OrthographicCamera();
		fitViewport = new FitViewport(WIDTH, HEIGHT, camera);
		fitViewport.apply();
		batch = new SpriteBatch();

		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
		res = new Content();
		res.loadAll();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(Betrayal.res.getTexture("splash"), 0, 0, 720, 1280);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		fitViewport.update(width, height);
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}

	@Override
	public Screen getScreen() {
		return super.getScreen();
	}
}
