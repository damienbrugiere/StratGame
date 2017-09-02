package com.mygdx.game.screen.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.ButtonApplication;
import com.mygdx.game.DofusLike;
import com.mygdx.game.StratGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 05/07/2017.
 */

public class GroupButton extends Stage {
    private ImageTextButton po;
    private List<Image> images;

    public GroupButton(final DofusLike dofusLike, String screen) {
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
                dofusLike.changeScreen("formation");
            }
        });
        this.addActor(button1);
        ButtonApplication button2 = new ButtonApplication("Haut faits", 100, 300, -50, Gdx.graphics.getHeight() - 450);
        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dofusLike.changeScreen("haut fait");
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
        switch (screen) {
            case "home":
                button4.isDown();
                break;
            case "recrutement":
                button.isDown();
                break;
        }
        po = new ImageTextButton("" + StratGame.information.getPo(), StratGame.styleButton1);
        po.setPosition(Gdx.graphics.getWidth() - 350, Gdx.graphics.getHeight() - 50);
        Image background = new Image(StratGame.textures.get("backgroundperso"));
        background.setX( po.getX());
        background.setY(0);
        background.setWidth(po.getWidth());
        background.setHeight(Gdx.graphics.getHeight() - po.getHeight());
        if(!this.getActors().contains(background,true)){
            this.addActor(background);
        }
        initTeam();
        this.addActor(po);
    }

    public void initTeam() {
        float x = po.getX() + 30;
        float y = po.getY() - 100;
        images = StratGame.information.getImages();
        if (images == null) {
            images = new ArrayList<>();
        }

        for (Image sprite : this.images) {
            sprite.setX(x);
            sprite.setY(y);
            if (!this.getActors().contains(sprite, false)) {
                this.addActor(sprite);
            }
            x += 100;
            if (x > Gdx.graphics.getWidth() - 100) {
                x = po.getX() + 30;
                y -= 50;
            }
        }
    }

    @Override
    public void act() {
        super.act();
        initTeam();
        po.setText(Integer.toString(StratGame.information.getPo()));
    }
}
