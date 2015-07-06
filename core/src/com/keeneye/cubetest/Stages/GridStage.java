package com.keeneye.cubetest.Stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
import com.keeneye.cubetest.CubeTest;
import com.keeneye.cubetest.Screens.OverScreen;
import com.keeneye.cubetest.Utils.HelperUtils;
import com.keeneye.cubetest.Utils.RandomUtils;

/**
 * Created by bazilm on 02-07-2015.
 */
public class GridStage extends Stage {

    private CubeTest game;

    private Grid grids[][];
    private BackgroundGrid back_grids[][];
    private ShapeRenderer renderer;
    private RandomUtils randomUtils;
    private HelperUtils helperUtils;
    private float spawn_time,background_change_time,second_tracker;
    private int game_time;

    private int score;
    private BitmapFont scoreFont;

    private Batch batch;

    private Preferences preferences;

    private boolean newScreen;

    private boolean pause;



    public GridStage(int width,int height,CubeTest game)
    {

        super(new ScalingViewport(Scaling.stretch,width,height,new OrthographicCamera(width,height)));

        this.game=game;

        preferences = Gdx.app.getPreferences("CubeTest");

        if(!preferences.contains("highscore"))
        {
            preferences.putInteger("highscore",0);
            preferences.flush();
        }

        randomUtils=new RandomUtils();
        helperUtils=new HelperUtils();

        grids = new Grid[8][6];
        back_grids=new BackgroundGrid[2][2];

        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(this.getCamera().combined);

        spawn_time=0;
        background_change_time=0;
        second_tracker=0;
        game_time=60;

        newScreen =false;
        pause=false;

        batch = getBatch();
        batch.setProjectionMatrix(getCamera().combined);

        score=0;
        scoreFont=new BitmapFont(Gdx.files.internal("skin/default.fnt"));
        scoreFont.setColor(0,0,0,1);

        Color color = randomUtils.getRandomColor();
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                back_grids[i][j]=new BackgroundGrid(j*240,120+(i*280),240,280,renderer);
                back_grids[i][j].setColor(color);
                addActor(back_grids[i][j]);

            }
        }

        for(int i=0;i<8;i++)
        {
            for(int j=0;j<6;j++)
            {
                grids[i][j] = new Grid(60*(j+1),160+(60*(i)),60,60,renderer,this);
                grids[i][j].setBack_color(color);
                addActor(grids[i][j]);


            }

        }




        Gdx.input.setInputProcessor(this);


    }



    @Override
    public void act(float delta) {

        if(!pause) {
            super.act(delta);

            spawn_time += delta;
            background_change_time += delta;
            second_tracker += delta;
            //Gdx.app.log("GridStage","Act");
            if (!newScreen) {
                if (game_time <= 0) {

                    int highscore = preferences.getInteger("highscore");

                    if (score > highscore) {
                        preferences.putInteger("highscore", score);
                        preferences.flush();
                    }
                    //this.dispose();
                    newScreen = true;
                    game.setScreen(new OverScreen(game, this, score));


                }
            }


            if (second_tracker >= 1) {
                game_time--;

                second_tracker = 0;
            }


            if (spawn_time > 0.35) {
                spawn_time = 0;
                int x = randomUtils.getRandomInt(8);
                int y = randomUtils.getRandomInt(6);
                if (!grids[x][y].isDraw()) {
                    grids[x][y].setColor(randomUtils.getRandomColor());
                    grids[x][y].setDraw(true);

                    if (randomUtils.getRandomBoolean(8))
                        grids[x][y].setDraw_clock(true);

                    if (randomUtils.getRandomBoolean(12))
                        grids[x][y].setDraw_special(true);

                }
            }

            if (background_change_time > 10) {
                background_change_time = 0;
                int x = randomUtils.getRandomInt(2);
                int y = randomUtils.getRandomInt(2);
                Color color = randomUtils.getRandomColor();
                back_grids[x][y].setColor(color);
                helperUtils.set_grid_back_color(grids, x, y, color);

                int rnd = randomUtils.getRandomInt(2);

                if (rnd == 0) {
                    back_grids[(x + 1) % 2][y].setColor(color);
                    helperUtils.set_grid_back_color(grids, (x + 1) % 2, y, color);
                } else {
                    back_grids[x][(y + 1) % 2].setColor(color);
                    helperUtils.set_grid_back_color(grids, x, (y + 1) % 2, color);
                }


            }

        }
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    @Override
    public void draw() {
        if(!pause) {

            super.draw();
            batch.begin();
            scoreFont.draw(batch, Integer.toString(score), getWidth() - 50, getHeight() - 50);
            scoreFont.draw(batch, Integer.toString(game_time), getWidth() / 2, getHeight() - 50);
            batch.end();
        }
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        boolean success=false;
        Vector2 touchPoint = this.screenToStageCoordinates(new Vector2(screenX,screenY));

        for(int i=0;i<8;i++)
        {
            for(int j=0;j<6;j++)
            {
               score+=grids[i][j].score(touchPoint);
            }

        }

        Gdx.app.log("Score:",Integer.toString(score));

        return super.touchDown(screenX, screenY, pointer, button);
    }

    public int tapAll()
    {
        int score=0;
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<6;j++)
            {
                score+=grids[i][j].tap();

            }
        }

        return score;


    }

    public void setGame_time(int game_time) {
        this.game_time = game_time;
    }

    public int getGame_time() {
        return game_time;
    }

    @Override
    public void dispose() {
        super.dispose();
        renderer.dispose();
        scoreFont.dispose();
        batch.dispose();
    }
}
