package com.keeneye.cubetest.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by bazilm on 03-07-2015.
 */
public class AssetsLoader {

    public static Music background;
    public static Sound tap,wrongtap;
    public static Texture frame;


    public static void load() {

        background = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
        tap = Gdx.audio.newSound(Gdx.files.internal("tap.wav"));
        wrongtap = Gdx.audio.newSound(Gdx.files.internal("wrongtap.wav"));
        frame= new Texture(Gdx.files.internal("frame.png"));
    }
}
