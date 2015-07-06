package com.keeneye.cubetest.Actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by bazilm on 02-07-2015.
 */
public class BackgroundGrid extends GameActor {


    private Pixmap pixmap;
    private Texture color_texture;



    public BackgroundGrid(float x, float y, float width, float height, ShapeRenderer renderer) {
        super(x, y, width, height, renderer);


    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        batch.draw(color_texture,x,y,width,height);


      }

    @Override
    public void setColor(Color color) {
        super.setColor(color);

    }

    public void setColor_texture(Texture color_texture) {
        this.color_texture = color_texture;
    }

}
