package com.jnv.betrayal.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.resources.ResourceLoader;
import com.jnv.betrayal.resources.SavedDataHandler;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.Socket;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class Betrayal extends Game {

	public final static int WIDTH = 720;
	public final static int HEIGHT = 1280;
	public static boolean DEBUG = false;
	private static boolean gamePaused;
	public final BetrayalAssetManager res = new BetrayalAssetManager();
	public final List<Character> characters = new ArrayList<Character>();
	public final List<Character> fools = new ArrayList<Character>();
	public GameStateManager gsm;
	private SpriteBatch sb;
	private OrthographicCamera worldCam;
	private StretchViewport stretchViewport;
	private Stage stage;
	private ResourceLoader loader;
	private Character currentCharacter;
	public SavedDataHandler savedDataHandler = new SavedDataHandler(this);

	public void create() {
		init();

		loader = new ResourceLoader(res);
		gsm = new GameStateManager(this);
		if (!gamePaused) gsm.setState(GameStateManager.State.SPLASH);
		resume();
	}

	public void loadCharacters(List<Character> alive, List<Character> dead) {
		characters.clear();
		fools.clear();
		for (Character c : alive) {
			characters.add(c);
		}
		for (Character c : dead) {
			fools.add(c);
		}
	}

	public void addFool(Character character) {
		boolean notHere = true;
		for (Character c : fools) {
			if (c.getId() == character.getId())
				notHere = false;
		}
		if (notHere) fools.add(character);
	}

	public void dispose() {
		stage.dispose();
		res.dispose();
		if (currentCharacter != null
				&& currentCharacter.getRoom() != null
				&& currentCharacter.getRoom().getSocket() != null
				&& currentCharacter.getRoom().getSocket().connected()) {
			currentCharacter.getRoom().getSocket().disconnect();
		}
	}

	public void pause() {
		gamePaused = true;
		gsm.pause();
	}

	public void resume() {
		if (gamePaused) {
			gamePaused = false;
			gsm.resume();
		}
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
		gamePaused = false;
		worldCam = new OrthographicCamera();
		stretchViewport = new StretchViewport(WIDTH, HEIGHT, worldCam);
		stretchViewport.apply();
		sb = new SpriteBatch();
		stage = new Stage(stretchViewport, sb);
		Gdx.input.setInputProcessor(stage);
		stage.setDebugUnderMouse(DEBUG);
		worldCam.position.set(worldCam.viewportWidth / 2, worldCam.viewportHeight / 2, 0);
	}

	// Getters
	public SpriteBatch getBatch() {
		return sb;
	}

	public Stage getStage() {
		return stage;
	}

	public ResourceLoader getResourceLoader() {
		return loader;
	}

	public Character getCurrentCharacter() {
		return currentCharacter;
	}

	public void setCurrentCharacter(Character currentCharacter) {
		this.currentCharacter = currentCharacter;
	}
}
