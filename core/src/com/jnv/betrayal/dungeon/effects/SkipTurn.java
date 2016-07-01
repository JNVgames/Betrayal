package com.jnv.betrayal.dungeon.effects;


import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;

public class SkipTurn extends Effect implements Skippable {

    public SkipTurn() {
    }

    public SkipTurn(Card src, ArrayList<Card> dest, boolean consistent) {
        super(src, dest, consistent);
    }

    @Override
    public void startEffect(Card card) {   }

    @Override
    public void endEffect(Card card) {    }

    @Override
    public void consistentEffect(Card card) {    }
}
