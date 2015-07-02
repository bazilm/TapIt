package com.keeneye.cubetest.Stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.keeneye.cubetest.Actors.BackgroundGrid;
import com.keeneye.cubetest.Actors.Grid;
import com.keeneye.cubetest.Utils.RandomUtils;

/**
 * Created by bazilm on 02-07-2015.
 */
public class GridStage extends Stage {

    private Grid grids[][];
    private BackgroundGrid back_grids[][];
    private ShapeRenderer renderer;
    private RandomUtils randomUtils;
    private float spawn_time,background_change_time;



    public GridStage(int width,int height)
    {

        super(new ScalingViewport(Scaling.stretch,width,height,new OrthographicCamera(width,height)));
        randomUtils=new RandomUtils();

        grids = new Grid[4][4];
        back_grids=new BackgroundGrid[2][2];

        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(this.getCamera().combined);

        spawn_time=0;
        background_change_time=0;


        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                back_grids[i][j]=new BackgroundGrid(j*240,i*400,240,400,renderer);
                back_grids[i][j].setColor(randomUtils.getRandomColor());
                addActor(back_grids[i][j]);


            }
        }

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                grids[i][j] = new Grid(80*(j+1),160+(80*(i+1)),80,80,renderer);
                addActor(grids[i][j]);

            }



        }



        Gdx.input.setInputProcessor(this);


    }

    @Override
    public void act(float delta) {
        super.act(delta);

        spawn_time+=delta;
        background_change_time+=delta;


        if(spawn_time>1) {
            spawn_time=0;
            int x = randomUtils.getRandomInt();
            int y = randomUtils.getRandomInt();
            if (!grids[x][y].isDraw()) {
                grids[x][y].setColor(randomUtils.getRandomColor());
                grids[x][y].setDraw(true);
            }
        }

        if(background_change_time>10)
        {
            background_change_time=0;
            int x = randomUtils.getRandomInt()%2;
            int y= randomUtils.getRandomInt()%2;

            back_grids[x][y].setColor(randomUtils.getRandomColor());

            back_grids[(x+randomUtils.getRandomInt())%2][(y+randomUtils.getRandomInt())%2].setColor(randomUtils.getRandomColor());




        }


    }



    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {





        return super.touchDown(screenX, screenY, pointer, button);
    }

}
