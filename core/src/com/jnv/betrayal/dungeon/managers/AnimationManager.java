package com.jnv.betrayal.dungeon.managers;


import com.jnv.betrayal.dungeon.ActionHandler.Action;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.CardEffects;

public class AnimationManager {

    public static void performAnimation(Action action) {

        switch (action.getActionType()) {
            case ATTACK:
                CardEffects.jump(action.getSrc());
                for (Card card : action.getDest()) {
                    CardEffects.damaged(card);
                }
                break;
            case DEFEND:
                CardEffects.jump(action.getSrc());
                for (Card card : action.getDest()) {
                    CardEffects.defend(card);
                }
                break;
            case FLEE:
                break;
            case ITEM:
                CardEffects.useItem(action.getSrc());
        }
    }
}
