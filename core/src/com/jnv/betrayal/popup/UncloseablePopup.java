package com.jnv.betrayal.popup;

import com.jnv.betrayal.main.Betrayal;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

/**
 * Created by jphan on 8/15/16.
 */
public class UncloseablePopup extends Popup {
    public UncloseablePopup(Betrayal game) {
        super(game);
        mask.clearListeners();
    }

}
