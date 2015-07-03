package com.keeneye.cubetest.Stages;

import com.badlogic.gdx.Gdx;
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


        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                back_grids[i][j]=new BackgroundGrid(j*240,160+(i*240),240,240,renderer);
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

        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                helperUtils.set_grid_back_color(grids,i,j,back_grids[i][j].getColor());
            }
        }


        Gdx.input.setInputProcessor(this);


    }

    @Override
    public void act(float delta) {
        super.act(delta);

        spawn_time+=delta;
        background_change_time+=delta;


        if(spawn_time>0.4) {
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
            helperUtils.set_grid_back_color(grids,x,y,back_grids[x][y].getColor());

            back_grids[(x+randomUtils.getRandomInt())%2][(y+randomUtils.getRandomInt())%2].setColor(randomUtils.getRandomColor());
            helperUtils.set_grid_back_color(grids,(x+randomUtils.getRandomInt())%2,(y+randomUtils.getRandomInt())%2,
                back_grids[(x+randomUtils.getRandomInt())%2][(y+randomUtils.getRandomInt())%2].getColor());



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
                if(grids[i][j].contains(touchPoint))
                {
                    success = true;
                    break;
                }

            }
            if(success)
                break;
        }

        if(success)
            score+=10;

        else
            score-=10;


        Gdx.app.log("Score:",Integer.toString(score));

        return super.touchDown(screenX, screenY, pointer, button);
    }

}
