package com.keeneye.cubetest.Actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by bazilm on 02-07-2015.
 */
public class Grid extends GameActor {

    protected float time;


    public Grid(float x, float y, float width, float height, ShapeRenderer renderer) {
        super(x, y, width, height, renderer);
        this.time=0;
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);


        renderer.setAutoShapeType(true);
        renderer.begin();

        renderer.setColor(Color.WHITE);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.rect(x, y, width, height);

        renderer.setColor(Color.BLACK);
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.rect(x, y, width, height);
        renderer.end();



        if(draw) {


            renderer.begin();
            renderer.set(ShapeRenderer.ShapeType.Filled);
            renderer.setColor(color);

            renderer.rect(x, y, width, height);
            renderer.end();
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(draw)
        {
            time+=delta;

        }

        if(time>2)
        {
            time=0;
            draw=false;
        }
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }



}
