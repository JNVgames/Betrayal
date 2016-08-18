package com.jnv.betrayal.resources;


import com.badlogic.gdx.scenes.scene2d.Group;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.scene2d.ui.Image;

public class WhiteBoxOutline {
    protected Betrayal game;
    protected BetrayalAssetManager res;

    public WhiteBoxOutline(Betrayal game, Group group, float width, float height, float thickness, float x, float y){
        this.game = game;
        res = game.res;
        Image up = new Image(res.getTexture("white-pixel"));
        Image down = new Image(res.getTexture("white-pixel"));
        Image left = new Image(res.getTexture("white-pixel"));
        Image right = new Image(res.getTexture("white-pixel"));

        up.setWidth(width);
        up.setHeight(thickness);
        up.setX(x);
        up.setY(y + height);

        down.setWidth(width);
        down.setHeight(thickness);
        down.setX(x);
        down.setY(y);

        left.setWidth(thickness);
        left.setHeight(height);
        left.setX(x);
        left.setY(y);

        right.setWidth(thickness);
        right.setHeight(height);
        right.setX(x+width-thickness);
        right.setY(y);

        group.addActor(up);
        group.addActor(down);
        group.addActor(left);
        group.addActor(right);
    }


}
