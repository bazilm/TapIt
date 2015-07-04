package com.keeneye.cubetest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.keeneye.cubetest.Screens.GridScreen;
import com.keeneye.cubetest.Screens.OverScreen;
import com.keeneye.cubetest.Utils.AssetsLoader;

public class CubeTest extends Game {
	SpriteBatch batch;

	private GridScreen gridScreen;
	private OverScreen overScreen;

	public GridScreen getGridScreen() {
		return gridScreen;
	}

	public void setGridScreen(GridScreen gridScreen) {
		this.gridScreen = gridScreen;
	}

	public OverScreen getOverScreen() {
		return overScreen;
	}

	public void setOverScreen(OverScreen overScreen) {
		this.overScreen = overScreen;
	}

	@Override
	public void create () {

		gridScreen=new GridScreen(this);
		overScreen=new OverScreen(this,gridScreen.getStage(),0);

		AssetsLoader.load();
		AssetsLoader.background.setLooping(true);
		AssetsLoader.background.setVolume(0.3f);
		AssetsLoader.background.play();


		setScreen(gridScreen);
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetsLoader.dispose();
	}
}
