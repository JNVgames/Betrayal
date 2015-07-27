package com.jnv.Betrayal.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jnv.Betrayal.Handlers.GameStateManager;

public class MenuState extends GameState {

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, com.jnv.Betrayal.Main.Betrayal.WIDTH, com.jnv.Betrayal.Main.Betrayal.HEIGHT);
        Gdx.app.log("MenuState", "MenuState constructor");
    }

    public void update(float dt) {

    }

    public void handleInput() {

    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0); // Set background to black
        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(com.jnv.Betrayal.Main.Betrayal.res.getTexture("start_background"), 0, 0, 720, 1280);
        sb.draw(com.jnv.Betrayal.Main.Betrayal.res.getTexture("new"), 104, 800, 512, 144);
        sb.draw(com.jnv.Betrayal.Main.Betrayal.res.getTexture("load"), 104, 600, 512, 144);
        sb.draw(com.jnv.Betrayal.Main.Betrayal.res.getTexture("options"), 104, 400, 512, 144);
        sb.end();
    }

    public void dispose() {

    }

    public OrthographicCamera getCam() {
        return super.getCam();
    }
}
