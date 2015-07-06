package com.keeneye.cubetest.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.keeneye.cubetest.Tweens.GameActorTween;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;

/**
 * Created by bazilm on 02-07-2015.
 */
public class BackgroundGrid extends GameActor {



    public BackgroundGrid(float x, float y, float width, float height, ShapeRenderer renderer) {
        super(x, y, width, height, renderer);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        alphaManager.update(Gdx.graphics.getDeltaTime());
        renderer.setAutoShapeType(true);
        renderer.begin();
        renderer.setColor(color);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.rect(x, y, width, height);
        renderer.end();







    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        Tween.set(this, GameActorTween.ALPHA).target(0);
        Tween.to(this, GameActorTween.ALPHA, 1000f).target(color.a).ease(TweenEquations.easeInOutQuad).start(alphaManager);
    }

    @Override
    public void setColorAlpha(float alpha) {
        super.setColorAlpha(alpha);
    }
}
