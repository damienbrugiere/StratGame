package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

public class StratGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private Stage stage;
	private World world;
	@Override
	public void create () {
		batch = new SpriteBatch();
		stage = new Stage();
		stage.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				System.out.println("touch dow");
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("touch up");
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				System.out.println("move");
			}
		});
		Gdx.input.setInputProcessor(stage);
		//world = new World(stage,10,10);
		int[][] croquis = {
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0}
		};
		world = new World(stage,croquis);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
	}
}
