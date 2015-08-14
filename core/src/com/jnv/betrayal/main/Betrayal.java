/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jnv.betrayal.entities.Item;
import com.jnv.betrayal.gamestates.Monster;
import com.jnv.betrayal.handlers.Content;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.utilities.TextureLoader;

public class Betrayal extends Game {

	private SpriteBatch sb;
	private OrthographicCamera worldCam;
	public final static int WIDTH = 720;
	public final static int HEIGHT = 1280;
	private StretchViewport stretchViewport;
	private Stage stage;
	private static FreeTypeFontGenerator generator;

	public static Content res;

	public GameStateManager gsm;
	
	public void create() {
		worldCam = new OrthographicCamera();
		stretchViewport = new StretchViewport(WIDTH, HEIGHT, worldCam);
		stretchViewport.apply();
		sb = new SpriteBatch();
        stage = new Stage(stretchViewport, sb);
		Gdx.input.setInputProcessor(stage);
        stage.setDebugUnderMouse(true);

        worldCam.position.set(worldCam.viewportWidth / 2, worldCam.viewportHeight / 2, 0);

        res = new Content();
        TextureLoader.loadAll();
		Item.loadAll();
		Monster.loadMonsters();

		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/HURTMOLD.ttf"));

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
    public Screen getScreen() {
        return super.getScreen();
    }
	public Stage getStage() { return stage; }
	public static Label.LabelStyle getHurtmoldFontLabelStyle(int fontSize) {
		FreeTypeFontGenerator.FreeTypeFontParameter fontDetails = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontDetails.size = fontSize;
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = generator.generateFont(fontDetails);
		return labelStyle;
	}

    // Setters
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }

}
