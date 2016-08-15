package com.jnv.betrayal.popup;

import com.jnv.betrayal.main.Betrayal;

/**
 * Created by jphan on 8/15/16.
 */
public class UncloseablePopup extends Popup {
    public UncloseablePopup(Betrayal game) {
        super(game);
        mask.clearListeners();
    }

}
