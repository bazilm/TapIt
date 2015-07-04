package com.keeneye.cubetest.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.keeneye.cubetest.CubeTest;
import com.keeneye.cubetest.Stages.GridStage;

/**
 * Created by bazilm on 02-07-2015.
 */
public class GridScreen implements Screen {

    private static final int WIDTH = 480;
    private static final int HEIGHT = 800;

    private CubeTest game;

    private GridStage stage;
    private Batch batch;


    public GridScreen(CubeTest game)
    {
        init(game);

    }

    public void init(CubeTest game)
    {
        this.game=game;

    }

    public GridStage getStage() {
        return stage;
    }

    @Override
    public void show() {

        stage = new GridStage(WIDTH,HEIGHT,game);
        batch = stage.getBatch();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9f,0.9f,0.9f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

       /* batch.begin();
        batch.draw(AssetsLoader.frame,0,0,stage.getWidth(),stage.getHeight());
        batch.end();
*/
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
