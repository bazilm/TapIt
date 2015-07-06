package com.keeneye.cubetest.Enums;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by bazilm on 02-07-2015.
 */
public enum Colors {

    BLUE(new Color(0,0,0.8f,0.7f)),RED(new Color(0.9f,0,0,0.8f)),GREEN(new Color(0,0.8f,0,0.7f)),YELLOW(new Color(1,1,0,0.7f));

    private Color color;
    private Pixmap pixmap;
    private Texture color_texture,clock_texture,special_texture;

    Colors(Color color)
    {
        this.color=color;
        pixmap= new Pixmap(60,60, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillCircle(30,30,20);
        color_texture=new Texture(pixmap);

        pixmap.setColor(Color.WHITE);
        pixmap.drawCircle(30,30,15);
        clock_texture=new Texture(pixmap);

        pixmap.setColor(color);
        pixmap.fillRectangle(0,0,40,40);
        special_texture=new Texture(pixmap);

    }

    public Texture getClock_texture() {
        return clock_texture;
    }

    public Texture getColor_texture() {
        return color_texture;
    }

    public Pixmap getPixmap() {
        return pixmap;
    }

    public Texture getSpecial_texture() {
        return special_texture;
    }

    public Color getColor()

    {
        return color;
    }
}
