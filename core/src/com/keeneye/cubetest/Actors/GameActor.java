package com.keeneye.cubetest.Actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by bazilm on 02-07-2015.
 */
public class GameActor extends Actor {

    protected float x,y,width,height;
    protected ShapeRenderer renderer;
    protected Color color;

    protected boolean draw;

    public GameActor(float x,float y,float width,float height,ShapeRenderer renderer)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.renderer=renderer;
        this.color=null;
        this.draw=false;

    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
