package com.keeneye.cubetest.Tweens;

import com.keeneye.cubetest.Actors.GameActor;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by bazilm on 06-07-2015.
 */
public class GameActorTween implements TweenAccessor<GameActor> {


    public static final int ALPHA = 1;

    @Override
    public int getValues(GameActor target, int tweenType, float[] returnValues) {

        switch(tweenType)
        {
            case ALPHA:
                returnValues[0]=target.getColor().a;
                return 1;

            default:
                assert false;
                return -1;

        }

    }

    @Override
    public void setValues(GameActor target, int tweenType, float[] newValues) {

        switch(tweenType)
        {
            case ALPHA:
                target.setColorAlpha(newValues[0]);
                break;

            default:
                assert false;
                break;



        }

    }
}
