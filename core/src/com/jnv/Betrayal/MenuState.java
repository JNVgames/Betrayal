package com.jnv.Betrayal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by jarnin on 7/20/15.
 */
public class MenuState extends GameState {
    protected MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Betrayal.WIDTH, Betrayal.HEIGHT);
        Gdx.app.log("MenuState", "MenuState constructor");
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0); // Set background to black
        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(Betrayal.res.getTexture("start_background"), 0, 0, 720, 1280);
        sb.draw(Betrayal.res.getTexture("new"), 104, 800, 512, 144);
        sb.draw(Betrayal.res.getTexture("load"), 104, 600, 512, 144);
        sb.draw(Betrayal.res.getTexture("options"), 104, 400, 512, 144);
        sb.end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public OrthographicCamera getCam() {
        return super.getCam();
    }
}
