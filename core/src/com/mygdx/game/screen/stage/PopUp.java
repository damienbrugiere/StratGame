package com.mygdx.game.screen.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.ButtonPopUpApplication;
import com.mygdx.game.DofusLike;
import com.mygdx.game.StratGame;
import com.mygdx.game.information.Information;


/**
 * Created by damien on 20/08/2017.
 */

public class PopUp extends Stage {
    public PopUp(Texture texture, final DofusLike dofusLike) {
        super();
        StratGame.information.checkTeam();
        Image image = new Image(new TextureRegion(texture));
        image.setX(Gdx.graphics.getWidth() / 2 - image.getWidth() / 2);
        image.setY(Gdx.graphics.getHeight() / 2 - image.getHeight() / 2);
        Image background = new Image(new TextureRegion(StratGame.textures.get("backgroundpopup")));
        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ButtonPopUpApplication buttonApplication = new ButtonPopUpApplication("OK", image.getX() + image.getWidth() / 2, image.getY());
        buttonApplication.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dofusLike.changeScreen("home");
            }
        });
        this.addActor(background);
        this.addActor(image);
        this.addActor(buttonApplication);
    }

}
