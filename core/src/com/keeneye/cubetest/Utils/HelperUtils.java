package com.keeneye.cubetest.Utils;

import com.badlogic.gdx.graphics.Color;
import com.keeneye.cubetest.Actors.Grid;

/**
 * Created by bazilm on 03-07-2015.
 */
public class HelperUtils {

    public static void set_grid_back_color(Grid[][] grids,int i,int j,Color color)
    {

        for(int a = i*4;a<(i*4)+4;a++)
        {
            for(int b=j*3;b<(j*3)+3;b++)
            {
             grids[a][b].setBack_color(color);
            }
        }


    }
}
