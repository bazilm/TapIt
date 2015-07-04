package com.keeneye.cubetest.Stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.keeneye.cubetest.Actors.BackgroundGrid;
import com.keeneye.cubetest.Actors.Grid;
import com.keeneye.cubetest.Utils.HelperUtils;
import com.keeneye.cubetest.Utils.RandomUtils;

/**
 * Created by bazilm on 02-07-2015.
 */
public class GridStage extends Stage {

    private Grid grids[][];
    private BackgroundGrid back_grids[][];
    private ShapeRenderer renderer;
    private RandomUtils randomUtils;
    private HelperUtils helperUtils;
    private float spawn_time,background_change_time;

    private int score;
    private BitmapFont scoreFont;

    private Batch batch;


    public GridStage(int width,int height)
    {

        super(new ScalingViewport(Scaling.stretch,width,height,new OrthographicCamera(width,height)));
        randomUtils=new RandomUtils();
        helperUtils=new HelperUtils();

        grids = new Grid[4][4];
        back_grids=new BackgroundGrid[2][2];

        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(this.getCamera().combined);

        spawn_time=0;
        background_change_time=0;

        batch = getBatch();
        batch.setProjectionMatrix(getCamera().combined);

        score=0;
        scoreFont=new BitmapFont(Gdx.files.internal("default.fnt"));

        Color color = randomUtils.getRandomColor();
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                back_grids[i][j]=new BackgroundGrid(j*240,160+(i*240),240,240,renderer);
                back_grids[i][j].setColor(color);
                addActor(back_grids[i][j]);

            }
        }

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                grids[i][j] = new Grid(80*(j+1),160+(80*(i+1)),80,80,renderer);
                grids[i][j].setBack_color(color);
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


        if(spawn_time>0.5) {
            spawn_time=0;
            int x = randomUtils.getRandomInt(4);
            int y = randomUtils.getRandomInt(4);
            if (!grids[x][y].isDraw()) {
                grids[x][y].setColor(randomUtils.getRandomColor());
                grids[x][y].setDraw(true);

                if(randomUtils.getRandomBoolean(16))
                    grids[x][y].setDraw_clock(true);

            }
        }

        if(background_change_time>10)
        {
            background_change_time=0;
            int x = randomUtils.getRandomInt(2);
            int y= randomUtils.getRandomInt(2);
            Color color = randomUtils.getRandomColor();
            back_grids[x][y].setColor(color);
            helperUtils.set_grid_back_color(grids,x,y,color);

            int rnd= randomUtils.getRandomInt(2);

            if(rnd==0)
            {
                back_grids[(x+1)%2][y].setColor(color);
                helperUtils.set_grid_back_color(grids,(x+1)%2,y,color);
            }
            else
            {
                back_grids[x][(y+1)%2].setColor(color);
                helperUtils.set_grid_back_color(grids,x,(x+1)%2,color);
            }



        }


    }

    @Override
    public void draw() {
        super.draw();
        batch.begin();
        scoreFont.draw(batch,Integer.toString(score),getWidth()-50,getHeight()-50);
        batch.end();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        boolean success=false;
        Vector2 touchPoint = this.screenToStageCoordinates(new Vector2(screenX,screenY));

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
               score+=grids[i][j].score(touchPoint);
            }

        }

        Gdx.app.log("Score:",Integer.toString(score));

        return super.touchDown(screenX, screenY, pointer, button);
    }

}
