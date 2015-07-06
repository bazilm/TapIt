package com.keeneye.cubetest.Actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.keeneye.cubetest.Tweens.GameActorTween;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by bazilm on 02-07-2015.
 */
public class GameActor extends Actor {

    protected float x,y,width,height;
    protected ShapeRenderer renderer;
    protected Color color;

    protected boolean draw;

    protected TweenManager alphaManager;

    public GameActor(float x,float y,float width,float height,ShapeRenderer renderer)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.renderer=renderer;
        this.color=null;
        this.draw=false;

        Tween.registerAccessor(this.getClass(), new GameActorTween());
        alphaManager =new TweenManager();

    }



    @Override
    public void setColor(Color color) {
        this.color=color;


    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

    }

    public void setColorAlpha(float alpha)
    {
        this.color.a=alpha;

    }
}
