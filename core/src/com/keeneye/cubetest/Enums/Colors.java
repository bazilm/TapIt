package com.keeneye.cubetest.Enums;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by bazilm on 02-07-2015.
 */
public enum Colors {

    BLUE(Color.BLUE),RED(Color.RED),GREEN(Color.GREEN),YELLOW(Color.YELLOW);

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
