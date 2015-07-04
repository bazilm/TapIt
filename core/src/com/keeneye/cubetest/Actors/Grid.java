package com.keeneye.cubetest.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.keeneye.cubetest.Stages.GridStage;
import com.keeneye.cubetest.Utils.AssetsLoader;

/**
 * Created by bazilm on 02-07-2015.
 */
public class Grid extends GameActor {

    protected float time;
    protected Color back_color;
    protected Rectangle bound;
    protected Circle clock;
    protected Circle circle;
    protected boolean draw_clock;
    private GridStage gridStage;



    public Grid(float x, float y, float width, float height, ShapeRenderer renderer,GridStage gridStage) {
        super(x, y, width, height, renderer);
        this.time=0;
        this.back_color=null;
        bound=new Rectangle(x,y,width,height);
        clock=new Circle(x+(width/2),y+(height/2),10);
        circle = new Circle(x+(width/2),y+(height/2),20);
        this.setBounds(x,y,width,height);
        this.gridStage=gridStage;

    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);


        renderer.setAutoShapeType(true);
        renderer.begin();

        renderer.setColor(Color.WHITE);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.rect(x, y, width, height);

        /*renderer.setColor(Color.BLACK);
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.rect(x, y, width, height);

        */
        renderer.end();

        if(draw) {

            Gdx.gl.glEnable(GL20.GL_BLEND);
            renderer.begin();
            renderer.set(ShapeRenderer.ShapeType.Filled);
            renderer.setColor(color);

            renderer.circle(circle.x, circle.y,circle.radius);

            if(draw_clock) {
                renderer.set(ShapeRenderer.ShapeType.Line);
                renderer.setColor(Color.WHITE);
                renderer.circle(clock.x, clock.y, clock.radius,60);
            }

            renderer.end();
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(draw)
        {
            time+=delta;

        }

        if(time>2)
        {
            time=0;
            draw=false;
            draw_clock=false;
        }
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public Color getBack_color() {
        return back_color;
    }

    public void setBack_color(Color back_color) {
        this.back_color = back_color;
       // Gdx.app.log("Grid","Back color set to ".concat(back_color.toString()));

    }

    public int score (Vector2 coords) {

            if (bound.contains(coords.x, coords.y)) {

                    if (back_color == color) {
                        // Gdx.app.log("Grid","Got a Cube");

                        if(draw_clock==true)
                            gridStage.setGame_time(gridStage.getGame_time()+4);

                        int score=(int)(2-time)*10;
                        draw = false;
                        draw_clock=false;
                        time = 0;
                        AssetsLoader.tap.play();
                        return score+10;
                    } else {
                        // Gdx.app.log("Grid", "Lost a Finger".concat(color.toString()).concat(" ").concat(back_color.toString()));
                        draw = false;
                        draw_clock=false;
                        time = 0;
                        AssetsLoader.wrongtap.play();
                        return -10;

                    }


            }
                else
                return 0;

        }

    public boolean isDraw_clock() {
        return draw_clock;
    }

    public void setDraw_clock(boolean draw_clock) {
        this.draw_clock = draw_clock;
    }


}
