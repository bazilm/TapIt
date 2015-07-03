package com.keeneye.cubetest.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by bazilm on 03-07-2015.
 */
public class AssetsLoader {

    public static Music background;
    public static Sound tap;


    public static void load() {

        background = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
        tap = Gdx.audio.newSound(Gdx.files.internal("tap.wav"));
    }
}
