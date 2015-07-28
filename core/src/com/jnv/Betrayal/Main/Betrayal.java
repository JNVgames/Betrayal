package com.jnv.Betrayal.Main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jnv.Betrayal.Handlers.Content;
import com.jnv.Betrayal.Handlers.GameStateManager;
import com.jnv.Betrayal.Utilities.TextureLoader;

public class Betrayal extends Game {

	private SpriteBatch sb;
	private OrthographicCamera worldCam;
	public static int WIDTH = 720;
	public static int HEIGHT = 1280;
	private FitViewport fitViewport;
	private Stage stage;

	public static Content res;

	public GameStateManager gsm;
	
	public void create() {
		worldCam = new OrthographicCamera();
		fitViewport = new FitViewport(WIDTH, HEIGHT, worldCam);
		fitViewport.apply();
		sb = new SpriteBatch();
        stage = new Stage(fitViewport, sb);
		Gdx.input.setInputProcessor(stage);

        worldCam.position.set(worldCam.viewportWidth / 2, worldCam.viewportHeight / 2, 0);

        res = new Content();
        TextureLoader.loadAll();

		gsm = new GameStateManager(this);
		gsm.setState(GameStateManager.State.SPLASH);
	}
	public void dispose() {
		super.dispose();
		stage.dispose();
	}
	public void pause() {
		super.pause();
	}
	public void resume() {
		super.resume();
	}
	public void render () {
		worldCam.update();
		sb.setProjectionMatrix(worldCam.combined);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
	}
	public void resize(int width, int height) {
		super.resize(width, height);
		fitViewport.update(width, height);
		worldCam.position.set(worldCam.viewportWidth / 2, worldCam.viewportHeight / 2, 0);
	}

    // Getters
    public SpriteBatch getBatch() {
        return sb;
    }
    public OrthographicCamera getCamera() {
        return worldCam;
    }
    public FitViewport getFitViewport() { return fitViewport; }
    public Screen getScreen() {
        return super.getScreen();
    }
	public Stage getStage() { return stage; }

    // Setters
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }

}
