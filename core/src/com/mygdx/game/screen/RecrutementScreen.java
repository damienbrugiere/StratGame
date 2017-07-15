package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.mygdx.game.DofusLike;
import com.mygdx.game.screen.stage.GroupButton;
import com.mygdx.game.screen.stage.RecrutementSelect;

/**
 * Created by damien on 06/07/2017.
 */

public class RecrutementScreen implements Screen {
    private RecrutementSelect select;
    private GroupButton buttons;
    private DofusLike dofusLike;
    public RecrutementScreen(DofusLike dofusLike) {
        this.dofusLike = dofusLike;
    }

    @Override
    public void show() {
        select = new RecrutementSelect();
        buttons = new GroupButton(dofusLike,"recrutement");
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(buttons);
        multiplexer.addProcessor(select);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        select.act();
        select.draw();
        buttons.act();
        buttons.draw();
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
        select.dispose();
        buttons.dispose();
    }
}
