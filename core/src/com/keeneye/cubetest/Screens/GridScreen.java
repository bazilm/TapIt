package com.keeneye.cubetest.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.keeneye.cubetest.Stages.GridStage;

/**
 * Created by bazilm on 02-07-2015.
 */
public class GridScreen implements Screen {

    private static final int WIDTH = 480;
    private static final int HEIGHT = 800;

    private GridStage stage;



    @Override
    public void show() {

        stage = new GridStage(WIDTH,HEIGHT);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

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
