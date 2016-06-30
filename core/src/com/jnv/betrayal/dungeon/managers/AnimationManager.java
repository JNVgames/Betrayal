package com.jnv.betrayal.dungeon.managers;


import com.jnv.betrayal.dungeon.ActionHandler.Action;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.CardAnimation;

public class AnimationManager {

    public static void performAnimation(Action action) {

        switch (action.getActionType()) {
            case ATTACK:
                CardAnimation.jump(action.getSrc());
                for (Card card : action.getDest()) {
                    CardAnimation.damaged(card);
                }

                break;
            case DEFEND:
                CardAnimation.jump(action.getSrc());
                for (Card card : action.getDest()) {
                    CardAnimation.defend(card);
                }
                break;
            case FLEE:
                CardAnimation.fadeOut(action.getSrc());
                break;
            case ITEM:
                CardAnimation.useItem(action.getSrc());
                break;
        }
    }

    public static void healthBarAnimation(){

    }
}
