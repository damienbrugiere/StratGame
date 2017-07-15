package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.DofusLike;
import com.mygdx.game.StratGame;
import com.mygdx.game.screen.stage.GroupButton;
import com.mygdx.game.screen.stage.SelectLevel;


/**
 * Created by damien on 01/07/2017.
 */

public class HomeScreen implements Screen {

    private SelectLevel selectLevel;
    private DofusLike dofusLike;
    private GroupButton buttons;

    public HomeScreen(DofusLike dofusLike) {
        this.dofusLike = dofusLike;
    }

    @Override
    public void show() {
        selectLevel = new SelectLevel(dofusLike);
        this.buttons = new GroupButton(dofusLike,"home");
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(buttons);
        multiplexer.addProcessor(selectLevel);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        this.buttons.act(delta);
        this.buttons.draw();
        selectLevel.act(delta);
        selectLevel.draw();
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
        selectLevel.dispose();
        this.buttons.dispose();
    }
}
