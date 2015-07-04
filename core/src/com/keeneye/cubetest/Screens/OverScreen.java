package com.keeneye.cubetest.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.keeneye.cubetest.CubeTest;
import com.keeneye.cubetest.Stages.GridStage;

/**
 * Created by bazilm on 21-06-2015.
 */
public class OverScreen implements Screen{


    private static final int VIEWPORT_WIDTH = 480;
    private static final int VIEWPORT_HEIGHT = 800;


    private CubeTest game;
    private GridStage gridStage;

    private Stage stage;

    private Skin _skin;

    private Label title,highScore;
    private TextButton resume,options,exit,back;
    private Table table;
    private ShapeRenderer renderer;
    private Camera camera;

    private int score;
    private Preferences preferences;

    public OverScreen(CubeTest game, GridStage gridStage, int score)
    {
        this.game=game;
        this.gridStage=gridStage;
        this.score = score;
        preferences = Gdx.app.getPreferences("CubeTest");
    }

    @Override
    public void show() {



        renderer = new ShapeRenderer();

        stage = new Stage(new ScalingViewport(Scaling.stretch,VIEWPORT_WIDTH,VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH,VIEWPORT_HEIGHT)));

        _skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        table = new Table(_skin);
        table.setFillParent(true);
        table.center();


        title = new Label(Integer.toString(score), _skin);
        title.setFontScale(1.5f);

        highScore = new Label("High Score: " +Integer.toString(preferences.getInteger("highscore")),_skin );
        highScore.setFontScale(1f);
        resume = new TextButton("RETRY", _skin);


        options = new TextButton("OPTIONS", _skin);

        back = new TextButton("HOME",_skin);

        table.add(title).center().pad(2f);
        table.row().height(75);
        table.add(highScore).center().pad(5f);
        table.row().height(75);
        table.add(resume).center().width(300).pad(5f);
        table.row().height(75);
        table.add(back).center().width(300).pad(5f);
        table.row().height(75);
        table.add(options).center().width(300).pad(5f);



        table.setVisible(true);




        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);


    }



    @Override
    public void render(float delta) {

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gridStage.act(delta);
        gridStage.draw();

        Gdx.gl.glEnable(GL20.GL_BLEND);

        //camera.update();
        //renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(0, 0, 0, 0.5f);
        renderer.rect(-10, -10, Gdx.graphics.getWidth() + 20, Gdx.graphics.getHeight() + 20);
        renderer.end();


        stage.act(delta);
        stage.draw();

       /* if(resume.isChecked())
        {
            this.dispose();
            game.setScreen(new GameScreen(game));
        }
*/

       /* else if(back.isChecked())
        {
            this.dispose();
            game.setScreen(new MainScreen(game));
        }
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
        _skin.dispose();
        stage.dispose();


    }

}
