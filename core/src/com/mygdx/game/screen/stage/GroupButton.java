package com.mygdx.game.screen.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.ButtonApplication;
import com.mygdx.game.DofusLike;
import com.mygdx.game.StratGame;

/**
 * Created by damien on 05/07/2017.
 */

public class GroupButton extends Stage {
    public GroupButton(final DofusLike dofusLike,String screen) {
        super();
        final ButtonApplication button = new ButtonApplication("Recruter", 100, 300, -50, Gdx.graphics.getHeight() - 150);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dofusLike.changeScreen("recrutement");
            }
        });
        this.addActor(button);
        final ButtonApplication button1 = new ButtonApplication("Formation", 100, 300, -50, Gdx.graphics.getHeight() - 300);
        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action();
            }
        });
        this.addActor(button1);
        ButtonApplication button2 = new ButtonApplication("Haut faits", 100, 300, -50, Gdx.graphics.getHeight() - 450);
        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action();
            }
        });
        this.addActor(button2);

        final ButtonApplication button4 = new ButtonApplication("Home", 100, 300, -50, Gdx.graphics.getHeight() - 600);
        button4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dofusLike.changeScreen("home");
            }
        });
        this.addActor(button4);
        switch (screen){
            case "home":
                button4.isDown();
                break;
            case "recrutement":
                button.isDown();
                break;
        }
        ImageTextButton po = new ImageTextButton("" + StratGame.information.getPo(), StratGame.styleButton1);
        po.setPosition(Gdx.graphics.getWidth() - 350, Gdx.graphics.getHeight() - 50);
        this.addActor(po);

    }

    private void action() {

    }
}
