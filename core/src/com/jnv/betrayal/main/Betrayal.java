/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jnv.betrayal.Network.Player;
import com.jnv.betrayal.Network.PlayerStateHandler;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.resources.ResourceLoader;

public class Betrayal extends Game {

	public final static int WIDTH = 720;
	public final static int HEIGHT = 1280;
	public static boolean debug = true;
	private static boolean gamePaused;
	private static FreeTypeFontGenerator generator;
	public BetrayalAssetManager res;
	public GameStateManager gsm;
	private SpriteBatch sb;
	private OrthographicCamera worldCam;
	private StretchViewport stretchViewport;
	private Stage stage;
	private ResourceLoader loader;
	private Player player;

	public static Label.LabelStyle getFont(int fontSize) {
		FreeTypeFontGenerator.FreeTypeFontParameter fontDetails =
				new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontDetails.size = fontSize;
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = generator.generateFont(fontDetails);
		return labelStyle;
	}

	public void create() {
		init();

		res = new BetrayalAssetManager();
		loader = new ResourceLoader(res);
		player = new Player();

		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/HURTMOLD.ttf"));

		gsm = new GameStateManager(this);
		player = new Player();
		if (!gamePaused) gsm.setState(GameStateManager.State.SPLASH);

		resume();
	}

	public void dispose() {
		stage.dispose();
		res.clear();
	}

	public void pause() {
		PlayerStateHandler playerStateHandler = new PlayerStateHandler(player);
		playerStateHandler.goOffline(player.playerID);
		Gdx.app.log("Betrayal", "pause()");
        gamePaused = true;
		gsm.pause();
	}

	public void resume() {
        if (gamePaused) {
            gamePaused = false;
            gsm.resume();
        }
		Timer timer = new Timer();
		PlayerStateHandler playerStateHandler = new PlayerStateHandler(player);
		playerStateHandler.goOnline(player.playerID);
		Gdx.app.log("Betrayal", "resume()");
	}

	public void render() {
		worldCam.update();
		sb.setProjectionMatrix(worldCam.combined);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
	}

	public void resize(int width, int height) {
		super.resize(width, height);
		stretchViewport.update(width, height);
		worldCam.position.set(worldCam.viewportWidth / 2, worldCam.viewportHeight / 2, 0);
	}

	// Helpers
	private void init() {
		worldCam = new OrthographicCamera();
		stretchViewport = new StretchViewport(WIDTH, HEIGHT, worldCam);
		stretchViewport.apply();
		sb = new SpriteBatch();
		stage = new Stage(stretchViewport, sb);
		Gdx.input.setInputProcessor(stage);
		stage.setDebugUnderMouse(debug);
		worldCam.position.set(worldCam.viewportWidth / 2, worldCam.viewportHeight / 2, 0);
	}

	// Getters
	public Player getPlayer() {
		return player;
	}

	public SpriteBatch getBatch() {
		return sb;
	}

	public OrthographicCamera getCamera() {
		return worldCam;
	}

	public StretchViewport getStretchViewport() {
		return stretchViewport;
	}

	public Stage getStage() {
		return stage;
	}

	public ResourceLoader getResourceLoader() {
		return loader;
	}

}
