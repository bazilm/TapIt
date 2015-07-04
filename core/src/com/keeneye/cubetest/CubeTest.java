package com.keeneye.cubetest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.keeneye.cubetest.Screens.GridScreen;
import com.keeneye.cubetest.Utils.AssetsLoader;

public class CubeTest extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {

		setScreen(new GridScreen(this));
		AssetsLoader.load();
		AssetsLoader.background.setLooping(true);
		AssetsLoader.background.setVolume(0.3f);
		AssetsLoader.background.play();
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetsLoader.dispose();
	}
}
