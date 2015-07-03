package com.keeneye.cubetest.Actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by bazilm on 02-07-2015.
 */
public class Grid extends GameActor {

    protected float time;
    protected Color back_color;
    protected Rectangle bound;


    public Grid(float x, float y, float width, float height, ShapeRenderer renderer) {
        super(x, y, width, height, renderer);
        this.time=0;
        this.back_color=null;
        bound=new Rectangle(x,y,width,height);
        this.setBounds(x,y,width,height);

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

    public Color getBack_color() {
        return back_color;
    }

    public void setBack_color(Color back_color) {
        this.back_color = back_color;
       // Gdx.app.log("Grid","Back color set to ".concat(back_color.toString()));

    }

    public boolean contains (Vector2 coords)
    {
        if(bound.contains(coords.x,coords.y))
        {
            if(back_color==color)
            {
               // Gdx.app.log("Grid","Got a Cube");
                return true;
            }


            else {
               // Gdx.app.log("Grid", "Lost a Finger".concat(color.toString()).concat(" ").concat(back_color.toString()));
                return false;

            }

        }

        else
            return false;

    }

}
