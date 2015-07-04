package com.keeneye.cubetest.Enums;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by bazilm on 02-07-2015.
 */
public enum Colors {

    BLUE(new Color(0,0,0.8f,0.7f)),RED(new Color(0.9f,0,0,0.8f)),GREEN(new Color(0,0.8f,0,0.7f)),YELLOW(new Color(1,1,0,0.7f));

    private Color color;

    Colors(Color color)
    {
        this.color=color;
    }

    public Color getColor()
    {
        return color;
    }
}
