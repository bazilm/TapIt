package com.keeneye.cubetest.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by bazilm on 03-07-2015.
 */
public class AssetsLoader {

    public static Music background;
    public static Sound tap,wrongtap;



    public static void load() {

        background = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
        tap = Gdx.audio.newSound(Gdx.files.internal("tap.wav"));
        wrongtap = Gdx.audio.newSound(Gdx.files.internal("wrongtap.wav"));

    }

    public static void dispose()
    {

        background.dispose();
        tap.dispose();
        wrongtap.dispose();

    }
}
