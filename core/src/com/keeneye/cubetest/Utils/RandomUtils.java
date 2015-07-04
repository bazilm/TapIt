package com.keeneye.cubetest.Utils;

import com.badlogic.gdx.graphics.Color;
import com.keeneye.cubetest.Enums.Colors;

import java.util.Random;

/**
 * Created by bazilm on 02-07-2015.
 */
public class RandomUtils {

        private Random RND;
        private Colors[] values;

    public RandomUtils()
    {
        values = Colors.values();
        RND = new Random();

    }

    public Color getRandomColor()
    {
        return values[RND.nextInt(values.length)].getColor();


    }

    public int getRandomInt(int n)
    {
        return RND.nextInt(n);
    }

    public boolean getRandomBoolean(int n)
    {
       int r = getRandomInt(n);
       if(r==0)
           return true;
        else
           return false;


    }

}
