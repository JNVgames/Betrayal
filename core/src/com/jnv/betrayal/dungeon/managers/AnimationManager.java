package com.jnv.betrayal.dungeon.managers;


import com.jnv.betrayal.dungeon.ActionHandler.Action;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.CardEffects;

public class AnimationManager {

    public static void performAnimation(Action action) {
        CardEffects.attack(action.getSrc());

        switch (action.getActionType()) {
            case ATTACK:
                for (Card card : action.getDest()) {
                    CardEffects.damaged(card);
                }
                break;
            case DEFEND:
                break;
            case FLEE:
                break;
        }
    }
}
