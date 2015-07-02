package com.keeneye.cubetest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.keeneye.cubetest.Screens.GridScreen;

public class CubeTest extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {

		setScreen(new GridScreen());
	}


}
