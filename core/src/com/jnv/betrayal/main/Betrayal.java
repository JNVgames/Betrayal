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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jnv.betrayal.Network.Player;
import com.jnv.betrayal.entities.Item;
import com.jnv.betrayal.entities.Monster;
import com.jnv.betrayal.resources.Resources;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.resources.ResourceLoader;

public class Betrayal extends Game {

	private SpriteBatch sb;
	private OrthographicCamera worldCam;
	public final static int WIDTH = 720;
	public final static int HEIGHT = 1280;
	private StretchViewport stretchViewport;
	private static Stage stage;
    private static GameStateManager.State state;
    private static boolean gamePaused;
	private static FreeTypeFontGenerator generator;

	public static Resources res;

	public GameStateManager gsm;

	public Player player;
	
	public void create() {
		worldCam = new OrthographicCamera();
		stretchViewport = new StretchViewport(WIDTH, HEIGHT, worldCam);
		stretchViewport.apply();
		sb = new SpriteBatch();
        stage = new Stage(stretchViewport, sb);
		Gdx.input.setInputProcessor(stage);
        stage.setDebugUnderMouse(true);

        worldCam.position.set(worldCam.viewportWidth / 2, worldCam.viewportHeight / 2, 0);

        res = new Resources();
        ResourceLoader.loadAll();
		Item.loadAll();
		Monster.loadMonsters();

		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/HURTMOLD.ttf"));

		gsm = new GameStateManager(this);
		if (!gamePaused) gsm.setState(GameStateManager.State.SPLASH);
        resume();
	}
	public void dispose() {
		stage.dispose();
        res.removeAll();
	}
	public void pause() {
        gamePaused = true;
        state = gsm.currentState;
	}
	public void resume() {
        if (gamePaused) {
            gamePaused = false;
            gsm.setState(state);
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

    // Getters
    public SpriteBatch getBatch() {
        return sb;
    }
    public OrthographicCamera getCamera() {
        return worldCam;
    }
    public StretchViewport getStretchViewport() { return stretchViewport; }
	public Stage getStage() { return stage; }
	public static Label.LabelStyle getHurtmoldFontLabelStyle(int fontSize) {
		FreeTypeFontGenerator.FreeTypeFontParameter fontDetails =
				new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontDetails.size = fontSize;
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = generator.generateFont(fontDetails);
		return labelStyle;
	}

}
